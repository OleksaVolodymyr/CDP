package com.epam.lab.wait;

import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.support.ui.ExpectedConditions;
import org.openqa.selenium.support.ui.FluentWait;

import java.time.Duration;
import java.util.NoSuchElementException;

public class Wait {

    private static final int TIMEOUT_DURATION = 1;
    private static final int POLLING_DURATION = 3;

    private Wait() {
    }

    public static void waitForElementDisplayed(WebDriver driver, final String locator) {
        waitForElementDisplayed(driver, locator, TIMEOUT_DURATION, POLLING_DURATION);
    }

    public static void waitForElementDisplayed(WebDriver driver, final String locator, int timeout, int frequency) {
        new FluentWait<>(driver)
                .withTimeout(Duration.ofMinutes(timeout))
                .pollingEvery(Duration.ofSeconds(frequency))
                .ignoring(NoSuchElementException.class)
                .until(ExpectedConditions.refreshed(ExpectedConditions.visibilityOfElementLocated(By.xpath(locator))));
    }


}
