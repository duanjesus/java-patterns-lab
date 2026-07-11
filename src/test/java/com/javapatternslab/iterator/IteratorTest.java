package com.javapatternslab.iterator;

import org.junit.jupiter.api.Test;

import java.util.ArrayList;
import java.util.Iterator;
import java.util.List;
import java.util.NoSuchElementException;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertFalse;
import static org.junit.jupiter.api.Assertions.assertThrows;

class IteratorTest {

    @Test
    void fullIterationVisitsEveryOrderInInsertionOrder() {
        OrderHistory history = new OrderHistory();
        history.add(new Order("ORD-1", "PAID"));
        history.add(new Order("ORD-2", "CREATED"));

        List<String> ids = new ArrayList<>();
        for (Order order : history) {
            ids.add(order.id());
        }

        assertEquals(List.of("ORD-1", "ORD-2"), ids);
    }

    @Test
    void paidOrdersFiltersOutEverythingElse() {
        OrderHistory history = new OrderHistory();
        history.add(new Order("ORD-1", "PAID"));
        history.add(new Order("ORD-2", "CREATED"));
        history.add(new Order("ORD-3", "PAID"));
        history.add(new Order("ORD-4", "CANCELLED"));

        List<String> paidIds = new ArrayList<>();
        for (Order order : history.paidOrders()) {
            paidIds.add(order.id());
        }

        assertEquals(List.of("ORD-1", "ORD-3"), paidIds);
    }

    @Test
    void iteratorThrowsAfterExhaustion() {
        OrderHistory history = new OrderHistory();
        history.add(new Order("ORD-1", "PAID"));

        Iterator<Order> iterator = history.iterator();
        iterator.next();

        assertFalse(iterator.hasNext());
        assertThrows(NoSuchElementException.class, iterator::next);
    }

    @Test
    void paidOrderIteratorThrowsWhenNoPaidOrdersExist() {
        OrderHistory history = new OrderHistory();
        history.add(new Order("ORD-1", "CREATED"));

        Iterator<Order> iterator = history.paidOrders().iterator();

        assertFalse(iterator.hasNext());
        assertThrows(NoSuchElementException.class, iterator::next);
    }
}
