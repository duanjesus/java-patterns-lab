package com.javapatternslab.factory;

import java.util.List;

public class FactoryMethodDemo {

    public static void main(String[] args) {
        List<NotificationFactory> factories = List.of(
                new EmailNotificationFactory("duan@javapatternslab.dev"),
                new SmsNotificationFactory("+55 11 91234-5678"),
                new PushNotificationFactory("device-token-abc123")
        );

        System.out.println("-- Factory Method: same dispatch() call, three concrete notifications --");
        for (NotificationFactory factory : factories) {
            System.out.println(factory.dispatch("Your order has shipped!"));
        }
    }
}
