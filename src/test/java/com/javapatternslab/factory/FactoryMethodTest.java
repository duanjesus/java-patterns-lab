package com.javapatternslab.factory;

import org.junit.jupiter.api.Test;

import static org.junit.jupiter.api.Assertions.assertTrue;

class FactoryMethodTest {

    @Test
    void emailFactoryProducesEmailNotification() {
        NotificationFactory factory = new EmailNotificationFactory("duan@javapatternslab.dev");

        String result = factory.dispatch("hello");

        assertTrue(result.contains("Emailed"));
        assertTrue(result.contains("duan@javapatternslab.dev"));
    }

    @Test
    void smsFactoryProducesSmsNotification() {
        NotificationFactory factory = new SmsNotificationFactory("+55 11 91234-5678");

        String result = factory.dispatch("hello");

        assertTrue(result.contains("Texted"));
        assertTrue(result.contains("+55 11 91234-5678"));
    }

    @Test
    void pushFactoryProducesPushNotification() {
        NotificationFactory factory = new PushNotificationFactory("device-token-abc123");

        String result = factory.dispatch("hello");

        assertTrue(result.contains("Pushed"));
        assertTrue(result.contains("device-token-abc123"));
    }
}
