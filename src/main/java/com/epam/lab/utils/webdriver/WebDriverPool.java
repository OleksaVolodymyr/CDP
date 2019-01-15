package com.epam.lab.utils.webdriver;

import org.openqa.selenium.WebDriver;
import org.openqa.selenium.chrome.ChromeDriver;

import java.util.ArrayList;
import java.util.Collections;
import java.util.List;
import java.util.concurrent.TimeUnit;

public class WebDriverPool{
   // private static Logger LOG = LoggerFactory.getLogger(WebDriverPool.class);
    private static ThreadLocal<WebDriver> pool = new ThreadLocal<>();
    private static List<WebDriver> driversInThread = Collections.synchronizedList(new ArrayList<>());

    public static synchronized WebDriver getInstance() {
        if (pool.get() == null) {
           // LOG.info("Create web driver, thread id : " + Thread.currentThread().getId());
            System.setProperty("webdriver.chrome.driver", "./resources/chromedriver.exe");
            WebDriver driver = new ChromeDriver();
            driver.manage().timeouts().pageLoadTimeout(50, TimeUnit.SECONDS);
            driver.manage().timeouts().implicitlyWait(60, TimeUnit.SECONDS);
           // driver.manage().window().maximize();
            //driver.navigate().to("https://booking.uz.gov.ua");
            driversInThread.add(driver);
            pool.set(driver);
        }
        return pool.get();
    }

    public static void closeAllWebDrivers() {
       // LOG.info("Close all drivers");
        for (WebDriver driver : driversInThread) {
            driver.quit();
        }
    }

    public static void navigateTo(String url) {
        // getInstance().navigate().to(url);
    }
}

