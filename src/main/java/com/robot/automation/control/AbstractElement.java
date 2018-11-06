package com.robot.automation.control;

import org.openqa.selenium.WebElement;

public abstract class AbstractElement {

    protected WebElement webElement;


    protected AbstractElement(WebElement webElement) {
        this.webElement = webElement;

    }

    public String getAttribute(String name) {
        return webElement.getAttribute(name);
    }
}
