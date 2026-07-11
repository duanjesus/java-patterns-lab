package com.javapatternslab.state;

import org.junit.jupiter.api.Test;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertTrue;

class StateTest {

    @Test
    void validTransitionsWalkThroughTheFullLifecycle() {
        OrderContext order = new OrderContext();
        assertEquals("CREATED", order.getStateName());

        order.pay();
        assertEquals("PAID", order.getStateName());

        order.ship();
        assertEquals("SHIPPED", order.getStateName());

        order.deliver();
        assertEquals("DELIVERED", order.getStateName());
    }

    @Test
    void cancelIsValidFromCreatedAndFromPaid() {
        OrderContext order = new OrderContext();
        order.cancel();
        assertEquals("CANCELLED", order.getStateName());

        OrderContext paidOrder = new OrderContext();
        paidOrder.pay();
        paidOrder.cancel();
        assertEquals("CANCELLED", paidOrder.getStateName());
    }

    @Test
    void invalidTransitionReturnsGuardMessageAndLeavesStateUnchanged() {
        OrderContext order = new OrderContext();

        String result = order.ship();

        assertTrue(result.contains("Cannot ship from CREATED state"));
        assertEquals("CREATED", order.getStateName());
    }

    @Test
    void terminalStateRejectsEveryAction() {
        OrderContext order = new OrderContext();
        order.pay();
        order.ship();
        order.deliver();

        String result = order.pay();

        assertTrue(result.contains("Cannot pay from DELIVERED state"));
        assertEquals("DELIVERED", order.getStateName());
    }
}
