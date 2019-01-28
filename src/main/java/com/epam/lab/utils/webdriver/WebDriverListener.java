package com.epam.lab.utils.webdriver;

import org.apache.log4j.Logger;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.events.AbstractWebDriverEventListener;

public class WebDriverListener extends AbstractWebDriverEventListener {
    private static Logger LOG = Logger.getLogger(WebDriverListener.class);

    @Override
    public void beforeClickOn(WebElement element, WebDriver driver) {
        LOG.info("Click on " + element.getTagName());
    }
}
