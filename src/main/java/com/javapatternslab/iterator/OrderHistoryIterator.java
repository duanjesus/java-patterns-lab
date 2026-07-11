package com.javapatternslab.iterator;

import java.util.Iterator;
import java.util.List;
import java.util.NoSuchElementException;

public class OrderHistoryIterator implements Iterator<Order> {

    private final List<Order> orders;
    private int position = 0;

    public OrderHistoryIterator(List<Order> orders) {
        this.orders = orders;
    }

    @Override
    public boolean hasNext() {
        return position < orders.size();
    }

    @Override
    public Order next() {
        if (!hasNext()) {
            throw new NoSuchElementException("No more orders");
        }
        return orders.get(position++);
    }
}
