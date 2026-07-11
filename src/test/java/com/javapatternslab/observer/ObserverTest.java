package com.javapatternslab.observer;

import org.junit.jupiter.api.Test;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertTrue;

class ObserverTest {

    @Test
    void allRegisteredObserversAreNotifiedOnStatusChange() {
        Order order = new Order("ORD-1001");
        AnalyticsTracker tracker = new AnalyticsTracker();
        order.addObserver(tracker);
        order.addObserver(new EmailNotifier());

        order.setStatus(OrderStatus.PAID);

        assertEquals(OrderStatus.PAID, order.getStatus());
        assertEquals(1, tracker.eventCount());
        assertEquals(OrderStatus.PAID, tracker.getRecordedEvents().get(0));
    }

    @Test
    void trackerAccumulatesEveryTransition() {
        Order order = new Order("ORD-1002");
        AnalyticsTracker tracker = new AnalyticsTracker();
        order.addObserver(tracker);

        order.setStatus(OrderStatus.PAID);
        order.setStatus(OrderStatus.SHIPPED);
        order.setStatus(OrderStatus.DELIVERED);

        assertEquals(3, tracker.eventCount());
        assertTrue(tracker.getRecordedEvents().containsAll(
                java.util.List.of(OrderStatus.PAID, OrderStatus.SHIPPED, OrderStatus.DELIVERED)));
    }

    @Test
    void orderWithNoObserversStillUpdatesItsOwnStatus() {
        Order order = new Order("ORD-1003");

        order.setStatus(OrderStatus.PAID);

        assertEquals(OrderStatus.PAID, order.getStatus());
    }
}
