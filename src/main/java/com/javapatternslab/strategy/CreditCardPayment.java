package com.javapatternslab.strategy;

import java.math.BigDecimal;
import java.math.RoundingMode;

public class CreditCardPayment implements PaymentStrategy {

    private static final BigDecimal FEE_RATE = new BigDecimal("0.029");

    private final String cardNumber;

    public CreditCardPayment(String cardNumber) {
        this.cardNumber = cardNumber;
    }

    @Override
    public String pay(BigDecimal amount) {
        BigDecimal fee = amount.multiply(FEE_RATE).setScale(2, RoundingMode.HALF_UP);
        BigDecimal total = amount.add(fee);
        String lastFour = cardNumber.substring(cardNumber.length() - 4);
        return "Charged R$" + total + " to credit card ending in " + lastFour
                + " (includes R$" + fee + " processing fee)";
    }
}
