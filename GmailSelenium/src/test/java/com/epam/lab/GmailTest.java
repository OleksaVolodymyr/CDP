package com.epam.lab;

import com.epam.lab.bo.GmailInboxPageBO;
import com.epam.lab.bo.GmailLoginPageBO;
import com.epam.lab.listener.CustomTestListener;
import com.epam.lab.model.User;
import com.epam.lab.model.UsersModel;
import com.epam.lab.page.GmailInboxPage;
import com.epam.lab.page.GmailLoginPage;
import com.epam.lab.util.Parser;
import com.epam.lab.util.logging.LogReporter;
import com.epam.lab.util.webdriver.WebDriverPool;
import org.openqa.selenium.WebDriver;
import org.testng.Assert;
import org.testng.annotations.*;
import org.uncommons.reportng.HTMLReporter;

import java.util.Iterator;


@Listeners({HTMLReporter.class, CustomTestListener.class, LogReporter.class})
public class GmailTest {

    private int amount;

    @BeforeMethod
    public void setUp() {
        this.amount = 3;

    }

    @Test(dataProvider = "users", threadPoolSize = 3)
    public void deleteTest(User user) {
        WebDriver driver = WebDriverPool.getInstance(WebDriverPool.Driver.CHROME);
        GmailLoginPageBO loginPageBO = new GmailLoginPageBO(new GmailLoginPage(driver));
        loginPageBO.loadUrl("https://mail.google.com/mail/");
        loginPageBO.login(user);
        Assert.assertTrue(loginPageBO.isLogged(user));
        GmailInboxPageBO inboxPageBO = new GmailInboxPageBO(new GmailInboxPage(driver));
        inboxPageBO.selectMessage(amount);
        inboxPageBO.deleteMessage();
        Assert.assertTrue(inboxPageBO.isMessageDeleted());
        inboxPageBO.restoreDeletedMessage();
        Assert.assertTrue(inboxPageBO.isMessageRestored());
    }


    @DataProvider(name = "users", parallel = true)
    public Iterator<User> getDataFromXML() {
        return Parser.<UsersModel>XMLParse("./resources/users.xml").getUsers().iterator();
    }

    @AfterTest
    public void close() {
        WebDriverPool.closeAllWebDrivers();
    }
}
