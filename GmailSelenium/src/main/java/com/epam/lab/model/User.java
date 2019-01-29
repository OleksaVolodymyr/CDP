package com.epam.lab.model;

import com.epam.lab.anotation.CSVElement;

public class User {
    @CSVElement
    private String login;
    @CSVElement
    private String password;

    public User() {

    }

    public User(String login, String password) {
        this.login = login;
        this.password = password;
    }

    public String getLogin() {
        return this.login;
    }

    public void setLogin(String login) {
        this.login = login;
    }

    public String getPassword() {
        return this.password;
    }

    public void setPassword(String password) {
        this.password = password;
    }

    @Override
    public String toString() {
        return "User [login=" + this.login + ", password=" + this.password + "]";
    }

}
