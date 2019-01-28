package com.epam.lab;

import com.epam.lab.bussinesobject.GmailInboxPageBO;
import com.epam.lab.bussinesobject.GmailLoginPageBO;
import com.epam.lab.listener.CustomTestListener;
import com.epam.lab.model.Message;
import com.epam.lab.model.User;
import com.epam.lab.model.UsersModel;
import com.epam.lab.pages.GmailInboxPage;
import com.epam.lab.pages.GmailLoginPage;
import com.epam.lab.properties.Property;
import com.epam.lab.utils.Parser;
import com.epam.lab.utils.logging.LogReporter;
import com.epam.lab.utils.webdriver.WebDriverPool;
import org.openqa.selenium.WebDriver;
import org.testng.Assert;
import org.testng.annotations.*;
import org.uncommons.reportng.HTMLReporter;

import java.util.Iterator;


@Listeners({HTMLReporter.class, CustomTestListener.class, LogReporter.class})
public class GmailTest {

    private Message message;
    private Property prop = Property.getInstance();
    private int amount;

    @BeforeTest
    public void setUp() {
        this.message = new Message.MessageBuilder().setSender(prop.getPropertyByKey("fromWho"))
                .setSubject(prop.getPropertyByKey("subject"))
                .setMessageText(prop.getPropertyByKey("message"))
                .build();
        this.amount = 3;
    }
/*
    @Test(dataProvider = "users", threadPoolSize = 3)
    public void loginTest(User user) {
        this.driver = WebDriverPool.getInstance();
        GmailLoginPageBO loginPageBO = new GmailLoginPageBO(new GmailLoginPage(driver));
        loginPageBO.loadUrl("https://mail.google.com/mail/");
        loginPageBO.login(user);
        Assert.assertTrue(loginPageBO.isLogged(user));
    }

    @Test(dependsOnMethods = {"loginTest"}, threadPoolSize = 3)
    public void findMessageTest() {
        inboxPageBO = new GmailInboxPageBO(new GmailInboxPage(driver));
        inboxPageBO.findMessage(message, amount);
        Assert.assertTrue(inboxPageBO.isMessageFound(amount));
    }


    @Test(dependsOnMethods = {"findMessageTest"}, threadPoolSize = 3)
    public void deleteMessage() {
        inboxPageBO.selectMessage();
        inboxPageBO.deleteMessage();
        Assert.assertTrue(inboxPageBO.isMessageDeleted(message, amount));
    }

    @Test(dependsOnMethods = {"deleteMessage"}, threadPoolSize = 3)
    public void restoreMessage() {
        inboxPageBO.restoreDeletedMessage();
        Assert.assertTrue(inboxPageBO.isMessageRestored(message, amount));
    }*/

    @Test(dataProvider = "users", threadPoolSize = 3)
    public void deleteTest(User user) {
        WebDriver driver = WebDriverPool.getInstance();
        GmailLoginPageBO loginPageBO = new GmailLoginPageBO(new GmailLoginPage(driver));
        loginPageBO.loadUrl("https://mail.google.com/mail/");
        loginPageBO.login(user);
        Assert.assertTrue(loginPageBO.isLogged(user));
        GmailInboxPageBO inboxPageBO = new GmailInboxPageBO(new GmailInboxPage(driver));
        inboxPageBO.findMessage(message, amount);
        Assert.assertTrue(inboxPageBO.isMessageFound(amount));
        inboxPageBO.selectMessage();
        inboxPageBO.deleteMessage();
        Assert.assertTrue(inboxPageBO.isMessageDeleted());
        inboxPageBO.restoreDeletedMessage();
        Assert.assertTrue(inboxPageBO.isMessageRestored());
        //  Assert.assertTrue(true);
    }

    @DataProvider(name = "users", parallel = true)
    public Iterator<User> getDataFromXML() {
        return Parser.<UsersModel>XMLParse("./resources/users.xml").getUsers().iterator();
    }

    @AfterTest
    public void close() {
        // driver.close();
        WebDriverPool.closeAllWebDrivers();
    }
}
