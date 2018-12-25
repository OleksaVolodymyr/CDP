package com.epam.lab.control;

import org.openqa.selenium.WebElement;

public class TableRow extends AbstractElement {

	public TableRow(WebElement webElement) {
		super(webElement);
	}

	public String getText() {
		return webElement.getText();
	}

	public void click() {
		webElement.click();
	}
}
