package com.javapatternslab.facade;

import org.junit.jupiter.api.Test;

import java.math.BigDecimal;
import java.util.ArrayList;
import java.util.List;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertTrue;

class FacadeTest {

    @Test
    void successfulOrderRunsAllFourStepsInOrder() {
        List<String> callLog = new ArrayList<>();
        CheckoutFacade facade = new CheckoutFacade(
                orderId -> { callLog.add("reserveStock"); return true; },
                (orderId, amount) -> { callLog.add("charge"); return true; },
                orderId -> { callLog.add("scheduleShipment"); return "TRK-1"; },
                (orderId, trackingCode) -> callLog.add("sendConfirmation"));

        String result = facade.placeOrder("ORD-1", new BigDecimal("100.00"));

        assertTrue(result.contains("placed successfully"));
        assertEquals(List.of("reserveStock", "charge", "scheduleShipment", "sendConfirmation"), callLog);
    }

    @Test
    void decliningPaymentStopsBeforeShippingAndNotification() {
        List<String> callLog = new ArrayList<>();
        CheckoutFacade facade = new CheckoutFacade(
                orderId -> { callLog.add("reserveStock"); return true; },
                (orderId, amount) -> { callLog.add("charge"); return false; },
                orderId -> { callLog.add("scheduleShipment"); return "TRK-1"; },
                (orderId, trackingCode) -> callLog.add("sendConfirmation"));

        String result = facade.placeOrder("ORD-2", new BigDecimal("100.00"));

        assertTrue(result.contains("payment declined"));
        assertEquals(List.of("reserveStock", "charge"), callLog);
    }

    @Test
    void outOfStockStopsBeforePayment() {
        List<String> callLog = new ArrayList<>();
        CheckoutFacade facade = new CheckoutFacade(
                orderId -> { callLog.add("reserveStock"); return false; },
                (orderId, amount) -> { callLog.add("charge"); return true; },
                orderId -> { callLog.add("scheduleShipment"); return "TRK-1"; },
                (orderId, trackingCode) -> callLog.add("sendConfirmation"));

        String result = facade.placeOrder("ORD-3", new BigDecimal("100.00"));

        assertTrue(result.contains("out of stock"));
        assertEquals(List.of("reserveStock"), callLog);
    }
}
