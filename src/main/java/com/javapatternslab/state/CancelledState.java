package com.javapatternslab.state;

public class CancelledState implements OrderState {

    @Override
    public String name() {
        return "CANCELLED";
    }
}
