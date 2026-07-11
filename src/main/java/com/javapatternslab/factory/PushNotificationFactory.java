package com.javapatternslab.factory;

public class PushNotificationFactory extends NotificationFactory {

    private final String deviceToken;

    public PushNotificationFactory(String deviceToken) {
        this.deviceToken = deviceToken;
    }

    @Override
    protected Notification createNotification() {
        return new PushNotification(deviceToken);
    }
}
