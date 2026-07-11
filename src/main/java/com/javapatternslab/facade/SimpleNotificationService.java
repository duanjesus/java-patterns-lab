package com.javapatternslab.facade;

public class SimpleNotificationService implements NotificationService {

    @Override
    public void sendConfirmation(String orderId, String trackingCode) {
        System.out.println("[Notification] Sent confirmation for " + orderId + " (tracking " + trackingCode + ")");
    }
}
