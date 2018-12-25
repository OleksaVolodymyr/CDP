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
import com.epam.lab.utils.webdriver.WebDriverPool;
import org.openqa.selenium.WebDriver;
import org.testng.Assert;
import org.testng.annotations.*;
import org.uncommons.reportng.HTMLReporter;

import java.util.Iterator;

@Listeners({HTMLReporter.class, CustomTestListener.class})
public class GmailTest {

    private Message message;
    private Property prop = Property.getInstance();
    private int amount;
    private GmailLoginPageBO loginPageBO;
    private GmailInboxPageBO inboxPageBO;
    private WebDriver driver;

    @BeforeClass
    public void setUp() {
        this.message = new Message.MessageBuilder().setSender(prop.getPropertyByKey("fromWho"))
                .setSubject(prop.getPropertyByKey("subject"))
                .setMessageText(prop.getPropertyByKey("message"))
                .build();
        this.amount = 3;
        this.driver = WebDriverPool.getInstance();
    }

    @Test(dataProvider = "users")
    public void loginTest(User user) {
        loginPageBO = new GmailLoginPageBO(new GmailLoginPage(driver));
        loginPageBO.loadUrl("https://mail.google.com/mail/");
        loginPageBO.login(user);
        Assert.assertTrue(loginPageBO.isLogged(user));
    }

    @Test(dependsOnMethods = {"loginTest"})
    public void findMessageTest() {
        inboxPageBO = new GmailInboxPageBO(new GmailInboxPage(driver));
        inboxPageBO.findMessage(message, amount);
        Assert.assertTrue(inboxPageBO.isMessageFound(amount));
    }


    @Test(dependsOnMethods = {"findMessageTest"})
    public void deleteMessage() {
        inboxPageBO.selectMessage();
        inboxPageBO.deleteMessage();
        Assert.assertTrue(inboxPageBO.isMessageDeleted(message, amount));
    }

    @Test(dependsOnMethods = {"deleteMessage"})
    public void restoreMessage() {
        inboxPageBO.restoreDeletedMessage();
        Assert.assertTrue(inboxPageBO.isMessageRestored(message, amount));
    }

    @DataProvider(name = "users", parallel = true)
    public Iterator<User> getDataFromXML() {
        return Parser.<UsersModel>XMLParse("./users.xml").getUsers().iterator();
    }

    @AfterClass
    public void close() {
        //driver.close();
    }
}
