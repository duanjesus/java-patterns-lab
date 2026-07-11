package com.javapatternslab.strategy;

import java.math.BigDecimal;

public class PixPayment implements PaymentStrategy {

    private final String pixKey;

    public PixPayment(String pixKey) {
        this.pixKey = pixKey;
    }

    @Override
    public String pay(BigDecimal amount) {
        return "Transferred R$%s via Pix to key %s (no fee)".formatted(amount, pixKey);
    }
}
