package com.epam.lab.control;

import org.openqa.selenium.WebElement;

public class CheckBox extends AbstractElement {

	public CheckBox(WebElement webElement) {
		super(webElement);
	}

	public boolean isSelected() {
		return webElement.isSelected();
	}

	public void click() {
		webElement.click();
	}

}
