package com.epam.lab.bo;

import com.epam.lab.page.GmailInboxPage;

public class GmailInboxPageBO {
    private GmailInboxPage gmailInboxPage;

    public GmailInboxPageBO(GmailInboxPage gmailInbox) {
        this.gmailInboxPage = gmailInbox;
    }

    public void selectMessage(int amount) {
        gmailInboxPage.selectMessage(amount);
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
