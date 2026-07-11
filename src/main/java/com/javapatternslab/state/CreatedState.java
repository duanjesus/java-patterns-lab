package com.javapatternslab.state;

public class CreatedState implements OrderState {

    @Override
    public String pay(OrderContext context) {
        context.setState(new PaidState());
        return "Payment received, order is now PAID";
    }

    @Override
    public String cancel(OrderContext context) {
        context.setState(new CancelledState());
        return "Order cancelled";
    }

    @Override
    public String name() {
        return "CREATED";
    }
}
