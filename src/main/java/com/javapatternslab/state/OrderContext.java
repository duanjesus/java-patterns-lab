package com.javapatternslab.state;

public class OrderContext {

    private OrderState state = new CreatedState();

    public void setState(OrderState state) {
        this.state = state;
    }

    public String getStateName() {
        return state.name();
    }

    public String pay() {
        return state.pay(this);
    }

    public String ship() {
        return state.ship(this);
    }

    public String deliver() {
        return state.deliver(this);
    }

    public String cancel() {
        return state.cancel(this);
    }
}
