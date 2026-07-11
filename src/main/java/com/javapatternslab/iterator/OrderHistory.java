package com.javapatternslab.iterator;

import java.util.ArrayList;
import java.util.Iterator;
import java.util.List;

public class OrderHistory implements Iterable<Order> {

    private final List<Order> orders = new ArrayList<>();

    public void add(Order order) {
        orders.add(order);
    }

    @Override
    public Iterator<Order> iterator() {
        return new OrderHistoryIterator(orders);
    }

    public Iterable<Order> paidOrders() {
        return () -> new PaidOrderIterator(orders);
    }
}
