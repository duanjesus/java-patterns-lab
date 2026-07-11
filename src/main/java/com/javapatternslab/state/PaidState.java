package com.javapatternslab.state;

public class PaidState implements OrderState {

    @Override
    public String ship(OrderContext context) {
        context.setState(new ShippedState());
        return "Order shipped, now SHIPPED";
    }

    @Override
    public String cancel(OrderContext context) {
        context.setState(new CancelledState());
        return "Order cancelled after payment (refund required)";
    }

    @Override
    public String name() {
        return "PAID";
    }
}
