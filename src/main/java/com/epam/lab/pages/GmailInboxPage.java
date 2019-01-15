package com.epam.lab.pages;

import com.epam.lab.control.Button;
import com.epam.lab.control.CheckBox;
import com.epam.lab.control.TableRow;
import com.epam.lab.exceptions.NoSuchMessageFoundException;
import com.epam.lab.model.Message;
import org.apache.log4j.Logger;
import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.support.FindBy;
import org.openqa.selenium.support.ui.ExpectedConditions;
import org.openqa.selenium.support.ui.FluentWait;
import org.openqa.selenium.support.ui.WebDriverWait;

import java.time.Duration;
import java.util.ArrayList;
import java.util.List;
import java.util.NoSuchElementException;

public class GmailInboxPage extends PageObject {
    private static final Logger LOG = Logger.getLogger(GmailInboxPage.class);
    private static final String SENDER_XPATH = ".//div[@class='yW']";
    private static final String SUBJECT_XPATH = ".//span[@class='bog']";
    private static final String TEXT_MESSAGE_XPATH = ".//span[@class='y2']";
    private static final String CHECKBOX_XPATH = ".//div[@role='checkbox']";
    private static final String UNDO_LINK_XPATH = "//span[@id='link_undo']";
    private static final String DELETE_BUTTON_XPATH = ".//div[@class ='asa']";
    private static final String UNDO_DELETE_BUTTON_XPATH = "//span[@id='link_undo']";
    private static final String INBOX_MESSAGE_LIST_XPATH = "//tr[contains(@class,'zA')]";

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

    private List<TableRow> foundMessages;

    public GmailInboxPage() {
    }

    public GmailInboxPage(WebDriver driver) {
        super(driver);
    }

    public List<TableRow> getFoundMessages() {
        if (foundMessages == null) throw new NoSuchMessageFoundException("There no message found with such template");
        return foundMessages;
    }

    public List<TableRow> findMessage(Message messageTemplate, int amount) {
        LOG.info("Searching " + amount + " message using template : " + messageTemplate + "Thread id: " +Thread.currentThread().getId());
        int count = 0;
        foundMessages = new ArrayList<>();
        for (TableRow message : inboxMessages) {
           /* new FluentWait<>(driver)
                    .withTimeout(Duration.ofMinutes(1))
                    .pollingEvery(Duration.ofSeconds(3))
                    .ignoring(NoSuchElementException.class)
                    .until(ExpectedConditions.presenceOfAllElementsLocatedBy(By.xpath(SENDER_XPATH)));*/
            new WebDriverWait(driver, 30)
                    .until(ExpectedConditions.refreshed(ExpectedConditions.elementToBeClickable(By.xpath(INBOX_MESSAGE_LIST_XPATH))));
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

    public void deleteSelectedMessage() {
        LOG.info("Click delete button on " + this.getClass().getSimpleName());
       new FluentWait<>(driver)
                .withTimeout(Duration.ofMinutes(1))
                .pollingEvery(Duration.ofSeconds(2))
                .ignoring(NoSuchElementException.class)
                .until(ExpectedConditions.elementToBeClickable(By.xpath(DELETE_BUTTON_XPATH)));
      /*  new WebDriverWait(driver, 20)
                .until(ExpectedConditions.elementToBeClickable(By.xpath(DELETE_BUTTON_XPATH)));*/
        deleteButton.click();
        //deleteButton.click();
    }

    public void undoDeleteOperation() {
        new FluentWait<>(driver)
                .withTimeout(Duration.ofMinutes(1))
                .pollingEvery(Duration.ofSeconds(2))
                .ignoring(NoSuchElementException.class)
                .until(ExpectedConditions.visibilityOfElementLocated(By.xpath(UNDO_LINK_XPATH)));
       /* new WebDriverWait(driver, 20)
                .until(ExpectedConditions.visibilityOfAllElementsLocatedBy(By.xpath(UNDO_LINK_XPATH)));*/
        undoButtron.click();
    }

    public boolean isMessageRestored() {
        return undoDelete.isDisplayed();
    }

    public String getMessage() {
        return undoDelete.getText();
    }

    public boolean isMessageFound(int amount) {
        return this.getFoundMessages().size() == amount;
    }
}
