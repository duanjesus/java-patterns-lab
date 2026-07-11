package com.javapatternslab.facade;

public class SimpleShippingService implements ShippingService {

    @Override
    public String scheduleShipment(String orderId) {
        String trackingCode = "TRK-" + orderId;
        System.out.println("[Shipping] Scheduled shipment for " + orderId + ", tracking " + trackingCode);
        return trackingCode;
    }
}
