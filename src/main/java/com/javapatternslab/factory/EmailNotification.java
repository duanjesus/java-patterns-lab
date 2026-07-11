package com.javapatternslab.factory;

public class EmailNotification implements Notification {

    private final String emailAddress;

    public EmailNotification(String emailAddress) {
        this.emailAddress = emailAddress;
    }

    @Override
    public String send(String message) {
        return "Emailed \"" + message + "\" to " + emailAddress;
    }
}
