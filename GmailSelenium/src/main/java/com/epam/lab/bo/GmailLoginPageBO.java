package com.epam.lab.bo;

import com.epam.lab.model.User;
import com.epam.lab.page.GmailLoginPage;

public class GmailLoginPageBO {

    private GmailLoginPage gmailLoginPage;

    public GmailLoginPageBO(GmailLoginPage loginPage) {
        this.gmailLoginPage = loginPage;
    }

    public void loadUrl(String url) {
        gmailLoginPage.goTo(url);
    }

    public void login(User user) {
        gmailLoginPage.enterLoginAndSubmit(user.getLogin());
        gmailLoginPage.enterPasswordAndSubmit(user.getPassword());
    }

    public boolean isLogged(User user) {
        return gmailLoginPage.isLogged(user.getLogin() + "@gmail.com");
    }
}
