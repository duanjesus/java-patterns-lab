package com.javapatternslab.factory;

public class SmsNotification implements Notification {

    private final String phoneNumber;

    public SmsNotification(String phoneNumber) {
        this.phoneNumber = phoneNumber;
    }

    @Override
    public String send(String message) {
        return "Texted \"" + message + "\" to " + phoneNumber;
    }
}
