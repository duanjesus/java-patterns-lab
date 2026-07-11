package com.javapatternslab.facade;

public interface NotificationService {

    void sendConfirmation(String orderId, String trackingCode);
}
