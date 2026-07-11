package com.javapatternslab.observer;

public class ObserverDemo {

    public static void main(String[] args) {
        Order order = new Order("ORD-1001");
        order.addObserver(new EmailNotifier());
        order.addObserver(new InventoryUpdater());
        order.addObserver(new AnalyticsTracker());

        System.out.println("-- Observer: one Order, three independent observers --");
        order.setStatus(OrderStatus.PAID);
        order.setStatus(OrderStatus.SHIPPED);
        order.setStatus(OrderStatus.DELIVERED);
    }
}
