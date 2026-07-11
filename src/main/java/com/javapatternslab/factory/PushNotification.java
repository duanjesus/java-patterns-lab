package com.javapatternslab.factory;

public class PushNotification implements Notification {

    private final String deviceToken;

    public PushNotification(String deviceToken) {
        this.deviceToken = deviceToken;
    }

    @Override
    public String send(String message) {
        return "Pushed \"" + message + "\" to device " + deviceToken;
    }
}
