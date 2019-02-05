package com.epam.lab.pages;

import com.epam.lab.utils.elementdecorator.ExtendedFieldDecorator;
import com.epam.lab.utils.webdriver.WebDriverPool;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.support.PageFactory;

public class PageObject {

    WebDriver driver;

    public PageObject(WebDriver driver) {
        this.driver = driver;
        PageFactory.initElements(new ExtendedFieldDecorator(driver), this);
    }

    public PageObject() {
        this(WebDriverPool.getInstance(WebDriverPool.Driver.CHROME));
    }

    public void goTo(String url) {
        driver.get(url);
    }
}
