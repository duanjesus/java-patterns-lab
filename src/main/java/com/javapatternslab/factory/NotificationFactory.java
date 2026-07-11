package com.javapatternslab.factory;

public abstract class NotificationFactory {

    protected abstract Notification createNotification();

    public String dispatch(String message) {
        Notification notification = createNotification();
        return notification.send(message);
    }
}
