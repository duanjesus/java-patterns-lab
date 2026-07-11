package com.javapatternslab.observer;

public class InventoryUpdater implements OrderObserver {

    @Override
    public void onStatusChanged(Order order, OrderStatus newStatus) {
        if (newStatus == OrderStatus.PAID) {
            System.out.println("[InventoryUpdater] Reserved stock for order " + order.getId());
        } else if (newStatus == OrderStatus.SHIPPED) {
            System.out.println("[InventoryUpdater] Released stock hold for order " + order.getId());
        }
    }
}
