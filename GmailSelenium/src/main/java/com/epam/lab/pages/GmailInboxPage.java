package com.epam.lab.pages;

import com.epam.lab.control.Button;
import com.epam.lab.control.CheckBox;
import com.epam.lab.control.Label;
import com.epam.lab.control.TableRow;
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

public class GmailInboxPage extends PageObject {
    private static final Logger LOG = LoggerFactory.getLogger(GmailInboxPage.class);
    private static final String SENDER_XPATH = ".//div[@class='yW']";
    private static final String SUBJECT_XPATH = ".//span[@class='bog']";
    private static final String TEXT_MESSAGE_XPATH = ".//span[@class='y2']";
    private static final String CHECKBOX_XPATH = ".//div[@role='checkbox']";
    private static final String UNDO_LINK_XPATH = "//span[@id='link_undo']";
    private static final String DELETE_BUTTON_XPATH = ".//div[@class ='asa']";
    private static final String UNDO_DELETE_BUTTON_XPATH = "//span[@id='link_undo']";
    private static final String INBOX_MESSAGE_LIST_XPATH = ".//tr[contains(@class, 'zA')]";
    private static final String RESTORED_MESSAGE_XPATH = "//span[@class = 'bAq']";

    @FindBy(xpath = CHECKBOX_XPATH)
    private List<CheckBox> checkbox;

    @FindBy(xpath = DELETE_BUTTON_XPATH)
    private Button deleteButton;

    @FindBy(xpath = UNDO_LINK_XPATH)
    private Button undoButtron;

    @FindBy(xpath = UNDO_DELETE_BUTTON_XPATH)
    private Button undoDelete;

    @FindBy(xpath = INBOX_MESSAGE_LIST_XPATH)
    private List<TableRow> inboxMessages;

    @FindBy(xpath = RESTORED_MESSAGE_XPATH)
    private Label restoredMessage;


    public GmailInboxPage() {
    }

    public GmailInboxPage(WebDriver driver) {
        super(driver);
    }


    public void selectMessage(int amount) {
        LOG.info("Click message checkBox on " + this.getClass().getSimpleName());
        int i = 0;
        List<TableRow> msg = new ArrayList<>(inboxMessages);
        System.out.println(inboxMessages.size() + "!!!!!");
        for (TableRow message : msg) {
            CheckBox checkBox = new CheckBox(message.findElement(By.xpath(CHECKBOX_XPATH)));
            if (!checkBox.isSelected() && i < amount) {
                checkBox.click();
                i++;
            }
        }
    }

    public void deleteSelectedMessage() {
        LOG.info("Click delete button on " + this.getClass().getSimpleName());
        new FluentWait<>(driver)
                .withTimeout(Duration.ofMinutes(1))
                .pollingEvery(Duration.ofSeconds(2))
                .ignoring(NoSuchElementException.class)
                .until(ExpectedConditions.refreshed(ExpectedConditions.visibilityOfElementLocated(By.xpath(DELETE_BUTTON_XPATH))));
        deleteButton.click();

    }

    public void undoDeleteOperation() {
        LOG.info("Restore deleted messages ... ");
        new FluentWait<>(driver)
                .withTimeout(Duration.ofMinutes(1))
                .pollingEvery(Duration.ofSeconds(2))
                .ignoring(NoSuchElementException.class)
                .until(ExpectedConditions.visibilityOfElementLocated(By.xpath(UNDO_LINK_XPATH)));
        undoButtron.click();
    }

    public boolean isMessagesDeleted() {
        return undoDelete.isDisplayed();
    }

    public boolean isMessageRestored() {
        return restoredMessage.isDisplayed();
    }

    public String getMessage() {
        return undoDelete.getText();
    }


}