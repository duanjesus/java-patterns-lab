package com.javapatternslab.facade;

import java.math.BigDecimal;

public class FacadeDemo {

    public static void main(String[] args) {
        System.out.println("-- Facade: one placeOrder() call, four subsystems underneath --");

        CheckoutFacade facade = new CheckoutFacade();
        System.out.println(facade.placeOrder("ORD-4001", new BigDecimal("249.90")));

        System.out.println();
        System.out.println("-- Declined payment short-circuits before shipping/notification --");
        CheckoutFacade decliningFacade = new CheckoutFacade(
                new SimpleInventoryService(),
                (orderId, amount) -> {
                    System.out.println("[Payment] Declined charge for " + orderId);
                    return false;
                },
                new SimpleShippingService(),
                new SimpleNotificationService());
        System.out.println(decliningFacade.placeOrder("ORD-4002", new BigDecimal("50.00")));
    }
}
