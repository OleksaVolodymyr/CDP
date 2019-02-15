package com.epam.lab.util.webdriver;

import com.epam.lab.property.PropertyReader;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.chrome.ChromeDriver;
import org.openqa.selenium.opera.OperaDriver;
import org.openqa.selenium.opera.OperaOptions;
import org.openqa.selenium.remote.DesiredCapabilities;

import java.io.File;
import java.util.ArrayList;
import java.util.Collections;
import java.util.List;
import java.util.Map;
import java.util.concurrent.ConcurrentHashMap;
import java.util.concurrent.TimeUnit;

public class WebDriverPoolFactory {
    // private static final Logger LOG = LoggerFactory.getLogger(WebDriverPoolFactory.class);
    private static ThreadLocal<WebDriver> chromePool = new ThreadLocal<>();
    private static ThreadLocal<WebDriver> firefoxPool = new ThreadLocal<>();
    private static ThreadLocal<WebDriver> operaPool = new ThreadLocal<>();
    private static Map<String, ThreadLocal<WebDriver>> driversPool;
    private static List<WebDriver> driverInThread = Collections.synchronizedList(new ArrayList<WebDriver>());

    static {
        driversPool = new ConcurrentHashMap<>();
        driversPool.put("CHROME", chromePool);
        driversPool.put("OPERA", operaPool);
        driversPool.put("FIREFOX", firefoxPool);
    }

    public static synchronized WebDriver getDriver(String browserName) {
        WebDriver driver;
        if (driversPool.get(browserName.toUpperCase()).get() == null) {
            switch (browserName.toUpperCase()) {
                case "CHROME":
                    // LOG.info("Create Chrome webdriver, thread id : " + Thread.currentThread().getId());
                    System.setProperty("webdriver.chrome.driver", "src/main/resources/drivers/chromedriver.exe");
                    driver = new ChromeDriver();
                    driver.manage().timeouts().pageLoadTimeout(50, TimeUnit.SECONDS);
                    driver.manage().timeouts().implicitlyWait(60, TimeUnit.SECONDS);
                    // driver.manage().window().maximize();
                    chromePool.set(driver);
                    driverInThread.add(driver);
                    break;
                case "OPERA":
                    //LOG.info("Create Opera webdriver, thread id : " + Thread.currentThread().getId());
                    System.setProperty("webdriver.opera.driver", "src/main/resources/drivers/operadriver.exe");
                    OperaOptions operaOptions = new OperaOptions();
                    operaOptions.setBinary(new File(PropertyReader.getInstance().getPropertyByKey("PathToOperaExe")));
                    driver = new OperaDriver(operaOptions);
                    driver.manage().timeouts().pageLoadTimeout(50, TimeUnit.SECONDS);
                    driver.manage().timeouts().implicitlyWait(60, TimeUnit.SECONDS);
                    driver.manage().window().maximize();
                    operaPool.set(driver);
                    driverInThread.add(driver);
                    break;
                case "Remote":
                    DesiredCapabilities capabilities = new DesiredCapabilities();
            }
        }
        return driversPool.get(browserName.toUpperCase()).get();
    }

    public static void closeAllDrivers() {
        //LOG.info("Close all drivers");
        for (WebDriver driver : driverInThread) {
            driver.quit();
        }
    }


}
