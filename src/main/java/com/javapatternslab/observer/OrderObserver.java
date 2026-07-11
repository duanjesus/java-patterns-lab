package com.javapatternslab.observer;

public interface OrderObserver {

    void onStatusChanged(Order order, OrderStatus newStatus);
}
