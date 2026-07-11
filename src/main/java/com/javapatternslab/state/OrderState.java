package com.javapatternslab.state;

public interface OrderState {

    default String pay(OrderContext context) {
        return invalid("pay");
    }

    default String ship(OrderContext context) {
        return invalid("ship");
    }

    default String deliver(OrderContext context) {
        return invalid("deliver");
    }

    default String cancel(OrderContext context) {
        return invalid("cancel");
    }

    String name();

    private String invalid(String action) {
        return "Cannot " + action + " from " + name() + " state";
    }
}
