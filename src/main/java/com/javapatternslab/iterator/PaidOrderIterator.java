package com.javapatternslab.iterator;

import java.util.Iterator;
import java.util.List;
import java.util.NoSuchElementException;

public class PaidOrderIterator implements Iterator<Order> {

    private final List<Order> orders;
    private int position = 0;

    public PaidOrderIterator(List<Order> orders) {
        this.orders = orders;
        skipToNextPaid();
    }

    @Override
    public boolean hasNext() {
        return position < orders.size();
    }

    @Override
    public Order next() {
        if (!hasNext()) {
            throw new NoSuchElementException("No more paid orders");
        }
        Order order = orders.get(position);
        position++;
        skipToNextPaid();
        return order;
    }

    private void skipToNextPaid() {
        while (position < orders.size() && !"PAID".equals(orders.get(position).status())) {
            position++;
        }
    }
}
