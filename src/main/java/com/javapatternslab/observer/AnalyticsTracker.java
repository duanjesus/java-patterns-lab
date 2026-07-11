package com.javapatternslab.observer;

import java.util.ArrayList;
import java.util.List;

public class AnalyticsTracker implements OrderObserver {

    private final List<OrderStatus> recordedEvents = new ArrayList<>();

    @Override
    public void onStatusChanged(Order order, OrderStatus newStatus) {
        recordedEvents.add(newStatus);
        System.out.println("[AnalyticsTracker] Recorded event: order " + order.getId() + " -> " + newStatus);
    }

    public int eventCount() {
        return recordedEvents.size();
    }

    public List<OrderStatus> getRecordedEvents() {
        return List.copyOf(recordedEvents);
    }
}
