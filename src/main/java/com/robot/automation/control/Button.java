package com.robot.automation.control;

import org.openqa.selenium.By;
import org.openqa.selenium.WebElement;

public class Button extends AbstractElement {

    public Button(WebElement webElement) {
        super(webElement);

    }

    public void click() {
        webElement.click();
    }

    public String getText() {
        return webElement.getText();
    }

    public Button findElement(By by){
        return new Button(this.webElement.findElement(by));
    }
}
