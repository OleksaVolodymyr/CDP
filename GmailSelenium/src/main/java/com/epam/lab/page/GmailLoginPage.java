package com.epam.lab.page;

import com.epam.lab.control.Button;
import com.epam.lab.control.Label;
import com.epam.lab.control.TextInput;
import com.epam.lab.wait.Wait;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.interactions.Actions;
import org.openqa.selenium.support.FindBy;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

public class GmailLoginPage extends PageObject {
    private static final Logger LOG = LoggerFactory.getLogger(GmailLoginPage.class);
    private static final String EMAIL_INPUT_XPATH = "//input[@type='email' and @id = 'identifierId']";
    private static final String EMAIL_BUTTON_NEXT_XPATH = "//div[@id='identifierNext']";
    private static final String PASSWORD_INPUT_XPATH = "//input[@type='password']";
    private static final String PASSWORD_BUTTON_NEXT_XPATH = "//div[@id='passwordNext']";
    private static final String USER_PICTURE_BUTTON_XPATH = "//header[@id='gb']//a[@role ='button' and contains(@href,'accounts.google.com/')]";
    private static final String LOGGED_USER_EMAIL_XPATH = "//div[@class = 'gb_Ob']";
    private static final String LOGGED_USER_EMAIL_XPATH1 = "//div[@class = 'gb_Ob']";

    @FindBy(xpath = EMAIL_INPUT_XPATH)
    private TextInput loginInput;

    @FindBy(xpath = EMAIL_BUTTON_NEXT_XPATH)
    private Button emailNextButton;

    @FindBy(xpath = PASSWORD_INPUT_XPATH)
    private TextInput passwordInput;

    @FindBy(xpath = PASSWORD_BUTTON_NEXT_XPATH)
    private Button passwordNextButton;

    @FindBy(xpath = USER_PICTURE_BUTTON_XPATH)
    private Button picture;

    @FindBy(xpath = LOGGED_USER_EMAIL_XPATH)
    private Label userInfo;

    public GmailLoginPage() {
    }

    public GmailLoginPage(WebDriver driver) {
        super(driver);
    }

    public void enterLoginAndSubmit(String login) {
        LOG.info("Enter login : {} on {}", login, this.getClass().getSimpleName());
        loginInput.sendKeys(login);
        emailNextButton.click();
    }

    public void enterPasswordAndSubmit(String password) {
        LOG.info("Entering password : {} on {}", password, this.getClass().getSimpleName());
        Wait.waitForElementDisplayed(driver, PASSWORD_INPUT_XPATH);
        passwordInput.sendKeys(password);
        passwordNextButton.click();
    }

    public boolean isLogged(String userName) {
        picture.click();
        return userInfo.getText().equals(userName);
    }
}
