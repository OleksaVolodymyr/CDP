package com.epam.lab.control;

import org.openqa.selenium.By;
import org.openqa.selenium.WebElement;

public abstract class AbstractElement {

    protected WebElement webElement;

    public AbstractElement(WebElement webElement) {
        this.webElement = webElement;
    }

    public boolean isDisplayed() {
        return webElement.isDisplayed();
    }

    public WebElement findElement(By by) {
        return webElement.findElement(by);
    }
}
