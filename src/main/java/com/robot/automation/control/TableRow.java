package com.robot.automation.control;

import org.openqa.selenium.By;
import org.openqa.selenium.WebElement;

public class TableRow extends AbstractElement {

	public TableRow(WebElement webElement) {
		super(webElement);
	}

	public String getText() {
		return webElement.getText();
	}

	public WebElement findElement(By by) {
		return webElement.findElement(by);
	}

	public void click() {
		webElement.click();
	}
}
