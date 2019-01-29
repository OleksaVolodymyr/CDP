package com.epam.lab.bussinesobject;

import com.epam.lab.model.Message;
import com.epam.lab.pages.GmailInboxPage;

public class GmailInboxPageBO {
    private GmailInboxPage gmailInboxPage;

    public GmailInboxPageBO(GmailInboxPage gmailInbox) {
        this.gmailInboxPage = gmailInbox;
    }

    public void findMessage(Message model, int amount) {
        gmailInboxPage.findMessage(model, amount);
    }

    public void selectMessage() {
        gmailInboxPage.selectMessage();
    }

    public boolean isMessageFound(int amount) {
        return gmailInboxPage.getFoundMessages().size() == amount;
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
