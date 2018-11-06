package com.robot.automation.pages;


import com.robot.automation.utils.webdriver.WebDriverPoolFactory;
import com.robot.automation.utils.elementdecorator.ExtendedFieldDecorator;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.support.PageFactory;

public  class PageObject {

    protected WebDriver driver;

    private PageObject(WebDriver driver) {
        this.driver = driver;
        PageFactory.initElements(new ExtendedFieldDecorator(driver), this);
    }

    public PageObject() {
        this(WebDriverPoolFactory.getDriver("CHROME"));
    }

    public void goTo(String url) {
        driver.get(url);
    }
}
