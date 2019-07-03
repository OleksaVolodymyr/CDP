package com.epam.lab.util.webdriver;

import org.openqa.selenium.Platform;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.chrome.ChromeDriver;
import org.openqa.selenium.opera.OperaDriver;
import org.openqa.selenium.remote.DesiredCapabilities;
import org.openqa.selenium.remote.RemoteWebDriver;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import java.net.MalformedURLException;
import java.net.URL;
import java.util.ArrayList;
import java.util.Collections;
import java.util.List;
import java.util.Objects;
import java.util.concurrent.TimeUnit;

public class WebDriverPool {
    private static final Logger LOG = LoggerFactory.getLogger(WebDriverPool.class);
    private static ThreadLocal<WebDriver> pool = new ThreadLocal<>();
    private static List<WebDriver> driversInThread = Collections.synchronizedList(new ArrayList<>());

    public static synchronized WebDriver getInstance(Driver driverType) {
        WebDriver driver = null;

        if (pool.get() == null) {
            LOG.info("Create web driver, thread id : {}", Thread.currentThread().getId());
            switch (driverType) {
                case CHROME:
                    System.setProperty("webdriver.chrome.driver", "./resources/chromedriver.exe");
                    driver = new ChromeDriver();
                    break;
                case REMOTE:
                    DesiredCapabilities capabilities = new DesiredCapabilities();
                    capabilities.setPlatform(Platform.LINUX);
                    capabilities.setBrowserName("chrome");
                    try {
                        driver = new RemoteWebDriver(new URL("http://localhost:4444/wd/hub"), capabilities);
                    } catch (MalformedURLException e) {
                        LOG.error(e.getMessage());
                    }
                    break;
                case OPERA:
                    driver = new OperaDriver();
                    break;
            }
            Objects.requireNonNull(driver).manage().timeouts().pageLoadTimeout(50, TimeUnit.SECONDS);
            driver.manage().timeouts().implicitlyWait(60, TimeUnit.SECONDS);
            driversInThread.add(driver);
            pool.set(driver);
        }
        return pool.get();
    }

    public static void closeAllWebDrivers() {
        LOG.info("Close all drivers");
        for (WebDriver driver : driversInThread) {
            driver.quit();
        }
    }

    public enum Driver {
        OPERA, CHROME, REMOTE
    }

}

