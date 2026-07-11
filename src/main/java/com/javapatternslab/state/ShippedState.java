package com.javapatternslab.state;

public class ShippedState implements OrderState {

    @Override
    public String deliver(OrderContext context) {
        context.setState(new DeliveredState());
        return "Order delivered, now DELIVERED";
    }

    @Override
    public String name() {
        return "SHIPPED";
    }
}
