package com.epam.lab.control;

import org.openqa.selenium.WebElement;

public class Label extends AbstractElement {

    public Label(WebElement webElement) {
        super(webElement);
    }

    public String getText() {
        return webElement.getText();
    }


}