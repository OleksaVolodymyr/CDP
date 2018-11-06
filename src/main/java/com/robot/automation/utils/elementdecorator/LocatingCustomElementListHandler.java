package com.robot.automation.utils.elementdecorator;

import java.lang.reflect.InvocationHandler;
import java.lang.reflect.InvocationTargetException;
import java.lang.reflect.Method;
import java.util.ArrayList;
import java.util.List;

import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.pagefactory.ElementLocator;

public class LocatingCustomElementListHandler<T> implements InvocationHandler {
	private final ElementLocator locator;
	private final Class<?> clazz;

	public LocatingCustomElementListHandler(ElementLocator locator, Class<?> clazz) {
		this.locator = locator;
		this.clazz = clazz;
	}

	@SuppressWarnings("unchecked")
	public Object invoke(Object object, Method method, Object[] objects) throws Throwable {
		List<WebElement> elements = locator.findElements();
		List<T> customs = new ArrayList<>();
		for (WebElement element : elements) {
			customs.add((T) clazz.getConstructor(WebElement.class).newInstance(element));
		}
		try {
			return method.invoke(customs, objects);
		} catch (InvocationTargetException e) {
			throw e.getCause();
		}
	}
}
