package com.robot.automation.control;

import org.openqa.selenium.By;
import org.openqa.selenium.WebElement;

public class Text extends AbstractElement {

    public Text(WebElement webElement) {
        super(webElement);
    }

    public String getText() {
        return webElement.getText();
    }

    public boolean isDisplayed() {
        return webElement.isDisplayed();
    }

    public void click() {
        webElement.click();
    }

    public Text findElement(By by) {
        return new Text(this.webElement.findElement(by));
    }

}