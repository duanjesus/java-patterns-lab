package com.javapatternslab.facade;

import java.math.BigDecimal;

public class CheckoutFacade {

    private final InventoryService inventory;
    private final PaymentService payment;
    private final ShippingService shipping;
    private final NotificationService notification;

    public CheckoutFacade() {
        this(new SimpleInventoryService(), new SimplePaymentService(),
                new SimpleShippingService(), new SimpleNotificationService());
    }

    public CheckoutFacade(InventoryService inventory, PaymentService payment,
                           ShippingService shipping, NotificationService notification) {
        this.inventory = inventory;
        this.payment = payment;
        this.shipping = shipping;
        this.notification = notification;
    }

    public String placeOrder(String orderId, BigDecimal amount) {
        if (!inventory.reserveStock(orderId)) {
            return "Order " + orderId + " failed: out of stock";
        }
        if (!payment.charge(orderId, amount)) {
            return "Order " + orderId + " failed: payment declined";
        }
        String trackingCode = shipping.scheduleShipment(orderId);
        notification.sendConfirmation(orderId, trackingCode);
        return "Order " + orderId + " placed successfully, tracking " + trackingCode;
    }
}
