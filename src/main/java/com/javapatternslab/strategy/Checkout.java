package com.javapatternslab.strategy;

import java.math.BigDecimal;
import java.util.Objects;

public class Checkout {

    private PaymentStrategy paymentStrategy;

    public Checkout(PaymentStrategy paymentStrategy) {
        this.paymentStrategy = Objects.requireNonNull(paymentStrategy);
    }

    public void setPaymentStrategy(PaymentStrategy paymentStrategy) {
        this.paymentStrategy = Objects.requireNonNull(paymentStrategy);
    }

    public String checkout(BigDecimal amount) {
        return paymentStrategy.pay(amount);
    }
}
