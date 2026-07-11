package com.javapatternslab.observer;

import java.util.ArrayList;
import java.util.List;

public class Order {

    private final String id;
    private OrderStatus status = OrderStatus.CREATED;
    private final List<OrderObserver> observers = new ArrayList<>();

    public Order(String id) {
        this.id = id;
    }

    public void addObserver(OrderObserver observer) {
        observers.add(observer);
    }

    public void setStatus(OrderStatus newStatus) {
        this.status = newStatus;
        for (OrderObserver observer : observers) {
            observer.onStatusChanged(this, newStatus);
        }
    }

    public String getId() {
        return id;
    }

    public OrderStatus getStatus() {
        return status;
    }
}
