package com.javapatternslab.state;

public class DeliveredState implements OrderState {

    @Override
    public String name() {
        return "DELIVERED";
    }
}
