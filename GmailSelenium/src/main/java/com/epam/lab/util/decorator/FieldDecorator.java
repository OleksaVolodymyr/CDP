package com.epam.lab.util.decorator;

import org.openqa.selenium.SearchContext;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.FindAll;
import org.openqa.selenium.support.FindBy;
import org.openqa.selenium.support.pagefactory.DefaultElementLocatorFactory;
import org.openqa.selenium.support.pagefactory.DefaultFieldDecorator;
import org.openqa.selenium.support.pagefactory.ElementLocator;

import java.lang.reflect.*;
import java.util.List;

public class FieldDecorator extends DefaultFieldDecorator {
    // private static final Logger LOG = LoggerFactory.getLogger(FieldDecorator.class);

    public FieldDecorator(SearchContext searchContext) {
        super(new DefaultElementLocatorFactory(searchContext));
    }

    @Override
    public Object decorate(ClassLoader loader, Field field) {
        if (field.isAnnotationPresent(FindBy.class) || field.isAnnotationPresent(FindAll.class)) {
            ElementLocator locator = factory.createLocator(field);
            if (locator == null) {
                return null;
            }
            if (List.class.isAssignableFrom(field.getType())) {
                return proxyForListLocator(loader, locator, field);
            }
            try {
                return proxyForLocator(loader, locator, field);
            } catch (InstantiationException | IllegalAccessException | IllegalArgumentException
                    | InvocationTargetException | NoSuchMethodException | SecurityException e) {
                e.printStackTrace();
            }
            return super.decorate(loader, field);
        } else return super.decorate(loader, field);
    }

    @SuppressWarnings("unchecked")
    private <T> T proxyForLocator(ClassLoader loader, ElementLocator locator, Field field)
            throws InstantiationException, IllegalAccessException, IllegalArgumentException, InvocationTargetException,
            NoSuchMethodException, SecurityException {
        WebElement proxy = proxyForLocator(loader, locator);
        Class<?> clazz = field.getType();
        // LOG.info("Create element " + clazz.getSimpleName());
        return (T) clazz.getConstructor(WebElement.class).newInstance(proxy);
    }

    @SuppressWarnings("unchecked")
    private <T> List<T> proxyForListLocator(ClassLoader loader, ElementLocator locator, Field field) {
        Type listType = field.getGenericType();
        Class<?> clazz = (Class<?>) ((ParameterizedType) listType).getActualTypeArguments()[0];
        //LOG.info("Create list of elements " + clazz.getSimpleName());
        if (clazz.isAssignableFrom(WebElement.class)) {
            return (List<T>) super.proxyForListLocator(loader, locator);
        } else {
            InvocationHandler handler = new ListHandler<>(locator, clazz);
            return (List<T>) Proxy.newProxyInstance(loader, new Class[]{List.class}, handler);
        }
    }

}