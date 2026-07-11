package com.javapatternslab.command;

import java.util.HashSet;
import java.util.Set;

/**
 * Minimal "receiver" the commands act on — stands in for a real
 * order-management service.
 */
public class OrderBook {

    private final Set<String> placedOrders = new HashSet<>();

    public void place(String orderId) {
        placedOrders.add(orderId);
    }

    public void cancel(String orderId) {
        placedOrders.remove(orderId);
    }

    public boolean isPlaced(String orderId) {
        return placedOrders.contains(orderId);
    }
}
