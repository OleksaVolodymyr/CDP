package com.epam.lab.pages;

import com.epam.lab.control.Button;
import com.epam.lab.control.CheckBox;
import com.epam.lab.control.TableRow;
import com.epam.lab.model.Message;
import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.support.FindBy;
import org.openqa.selenium.support.ui.ExpectedConditions;
import org.openqa.selenium.support.ui.FluentWait;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import java.time.Duration;
import java.util.ArrayList;
import java.util.List;
import java.util.NoSuchElementException;

public class GmailBinPage extends PageObject {
    private static final Logger LOG = LoggerFactory.getLogger(GmailBinPage.class);
    private static final String MORE_BUTTON_XPATH = "//span[@class ='CJ']";
    private static final String BIN_BUTTON_XPATH = "//span[@class ='nU ']/a[contains(@href, '#trash')]";
    private static final String MOVE_TO_XPATH = "//div[@id = ':ed']";
    private static final String INBOX_BUTTON_XPATH = "//div[@id = ':fd']";
    private static final String INBOX_MESSAGE_LIST_XPATH = "//tr[contains(@class,'zA')]";
    private static final String SENDER_XPATH = ".//div[@class='yW']";
    private static final String SUBJECT_XPATH = ".//span[@class='bog']";
    private static final String TEXT_MESSAGE_XPATH = ".//span[@class='y2']";
    private static final String CHECKBOX_XPATH = ".//div[@role='checkbox']";
    private static final String GMAIL_INBOX_BUTTON_XPATH = "//div[@id = ':cd']";

    @FindBy(xpath = MORE_BUTTON_XPATH)
    private Button moreButton;

    @FindBy(xpath = BIN_BUTTON_XPATH)
    private Button binButton;

    @FindBy(xpath = MOVE_TO_XPATH)
    private Button moveButton;

    @FindBy(xpath = INBOX_BUTTON_XPATH)
    private Button inboxButton;

    @FindBy(xpath = INBOX_MESSAGE_LIST_XPATH)
    private List<TableRow> inboxMessages;

    @FindBy(xpath = GMAIL_INBOX_BUTTON_XPATH)
    private Button gmailInboxButton;

    private List<TableRow> foundMessages;

    public GmailBinPage(WebDriver driver) {
        super(driver);
    }

    public List<TableRow> findMessage(Message messageTemplate, int amount) {
        LOG.info("Find " + amount + " message using template : " + messageTemplate);
        int count = 0;
        foundMessages = new ArrayList<>();
        for (TableRow message : inboxMessages) {
            new FluentWait<>(driver)
                    .withTimeout(Duration.ofMinutes(1))
                    .pollingEvery(Duration.ofSeconds(3))
                    .ignoring(NoSuchElementException.class)
                    .until(ExpectedConditions.visibilityOfElementLocated(By.xpath(SENDER_XPATH)));
            Message foundMessage = new Message.MessageBuilder().setSender(message.findElement(By.xpath(SENDER_XPATH)).getText().trim())
                    .setSubject(message.findElement(By.xpath(SUBJECT_XPATH)).getText().trim())
                    .setMessageText(message.findElement(By.xpath(TEXT_MESSAGE_XPATH)).getText().replaceAll("(^\\s+-\\s+)(\\n)", ""))
                    .build();
            if (messageTemplate.equals(foundMessage) && count < amount) {
                this.foundMessages.add(message);
                count++;
            }
        }
        return foundMessages;
    }

    public void selectMessage() {
        LOG.info("Click message checkBox on " + this.getClass().getSimpleName());
        for (TableRow message : foundMessages) {
            CheckBox checkBox = new CheckBox(message.findElement(By.xpath(CHECKBOX_XPATH)));
            if (!checkBox.isSelected()) {
                checkBox.click();
            }
        }
    }

    public void clickMoveToButton() {
        moreButton.click();
    }

    public void clickInboxButton() {
        inboxButton.click();
    }

    public void goToInbox() {
        gmailInboxButton.click();
    }
}
