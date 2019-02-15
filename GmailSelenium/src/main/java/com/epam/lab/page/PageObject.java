package com.epam.lab.page;

import com.epam.lab.util.decorator.FieldDecorator;
import com.epam.lab.util.webdriver.WebDriverPool;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.support.PageFactory;

public class PageObject {

    WebDriver driver;

    public PageObject(WebDriver driver) {
        this.driver = driver;
        PageFactory.initElements(new FieldDecorator(driver), this);
    }

    public PageObject() {
        this(WebDriverPool.getInstance(WebDriverPool.Driver.CHROME));
    }

    public void goTo(String url) {
        driver.get(url);
    }
}
