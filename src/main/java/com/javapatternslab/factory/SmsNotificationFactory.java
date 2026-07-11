package com.javapatternslab.factory;

public class SmsNotificationFactory extends NotificationFactory {

    private final String phoneNumber;

    public SmsNotificationFactory(String phoneNumber) {
        this.phoneNumber = phoneNumber;
    }

    @Override
    protected Notification createNotification() {
        return new SmsNotification(phoneNumber);
    }
}
