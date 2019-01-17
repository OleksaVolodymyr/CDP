package com.epam.lab;

import com.epam.lab.bussinesobject.GmailInboxPageBO;
import com.epam.lab.bussinesobject.GmailLoginPageBO;
import com.epam.lab.exceptions.NoSuchMessageFoundException;
import com.epam.lab.model.Message;
import com.epam.lab.model.User;
import com.epam.lab.model.UsersModel;
import com.epam.lab.pages.GmailInboxPage;
import com.epam.lab.pages.GmailLoginPage;
import com.epam.lab.properties.Property;
import com.epam.lab.utils.Parser;
import com.epam.lab.utils.webdriver.WebDriverPool;
import org.junit.jupiter.api.AfterAll;
import org.junit.jupiter.api.BeforeAll;
import org.junit.jupiter.api.TestInstance;
import org.junit.jupiter.api.parallel.Execution;
import org.junit.jupiter.api.parallel.ExecutionMode;
import org.junit.jupiter.params.ParameterizedTest;
import org.junit.jupiter.params.provider.CsvFileSource;
import org.junit.jupiter.params.provider.MethodSource;
import org.openqa.selenium.WebDriver;
import org.testng.Assert;

import java.util.stream.Stream;

//@Execution(ExecutionMode.CONCURRENT)
@TestInstance(TestInstance.Lifecycle.PER_CLASS)
public class GmailTest {

    private Message message;
    private Property prop = Property.getInstance();
    private int amount;

    static Stream<User> getUsers() {
        return Parser.<UsersModel>XMLParse("resources/users.xml").getUsers().stream();
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

    @BeforeAll
    public void setUp() {
        this.message = new Message.MessageBuilder().setSender(prop.getPropertyByKey("fromWho"))
                .setSubject(prop.getPropertyByKey("subject"))
                .setMessageText(prop.getPropertyByKey("message"))
                .build();
        this.amount = 3;
    }


    @ParameterizedTest
    @MethodSource("getUsers")
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
    }


    @AfterAll
    public void close() {
        // driver.close();
        WebDriverPool.closeAllWebDrivers();
    }
}
