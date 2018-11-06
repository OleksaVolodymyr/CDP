package com.robot.automation.control;

import org.openqa.selenium.WebElement;

public class Link extends AbstractElement {

    public Link(WebElement webElement) {
        super(webElement);
    }

    public String getUrl() {
        return webElement.getAttribute("href");
    }
}