package com.javapatternslab.factory;

public class EmailNotificationFactory extends NotificationFactory {

    private final String emailAddress;

    public EmailNotificationFactory(String emailAddress) {
        this.emailAddress = emailAddress;
    }

    @Override
    protected Notification createNotification() {
        return new EmailNotification(emailAddress);
    }
}
