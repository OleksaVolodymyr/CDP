package com.epam.lab.bo;

import com.epam.lab.model.Message;
import com.epam.lab.page.GmailInboxPage;

public class GmailInboxPageBO {
    private GmailInboxPage gmailInboxPage;

    public GmailInboxPageBO(GmailInboxPage gmailInbox) {
        this.gmailInboxPage = gmailInbox;
    }

    public void findMessage(Message model, int amount) {

    }

    public void selectMessage(int amount) {
        gmailInboxPage.selectMessage(amount);
    }

    public boolean isMessageFound(int amount) {
        return true;
    }

    public void deleteMessage() {
        gmailInboxPage.deleteSelectedMessage();
    }

    public boolean isMessageDeleted() {
        return gmailInboxPage.isMessagesDeleted();
    }

    public void restoreDeletedMessage() {
        gmailInboxPage.undoDeleteOperation();
    }

    public boolean isMessageRestored() {
        return gmailInboxPage.isMessageRestored();
    }

}
