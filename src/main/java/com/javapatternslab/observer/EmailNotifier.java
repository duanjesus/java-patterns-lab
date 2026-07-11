package com.javapatternslab.observer;

public class EmailNotifier implements OrderObserver {

    @Override
    public void onStatusChanged(Order order, OrderStatus newStatus) {
        System.out.println("[EmailNotifier] Emailed customer: order " + order.getId() + " is now " + newStatus);
    }
}
