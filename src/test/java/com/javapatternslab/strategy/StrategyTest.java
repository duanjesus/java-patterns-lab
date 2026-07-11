package com.javapatternslab.strategy;

import org.junit.jupiter.api.Test;

import java.math.BigDecimal;

import static org.junit.jupiter.api.Assertions.assertNotEquals;
import static org.junit.jupiter.api.Assertions.assertTrue;

class StrategyTest {

    @Test
    void creditCardPaymentAddsProcessingFee() {
        PaymentStrategy strategy = new CreditCardPayment("4111111111111234");

        String result = strategy.pay(new BigDecimal("100.00"));

        assertTrue(result.contains("102.90"), "expected 2.9% fee added to 100.00, got: " + result);
        assertTrue(result.contains("1234"), "expected only the last four digits to appear, got: " + result);
    }

    @Test
    void pixPaymentHasNoFee() {
        PaymentStrategy strategy = new PixPayment("duan@javapatternslab.dev");

        String result = strategy.pay(new BigDecimal("100.00"));

        assertTrue(result.contains("100.00"));
        assertTrue(result.contains("no fee"));
    }

    @Test
    void swappingStrategyChangesCheckoutBehavior() {
        BigDecimal amount = new BigDecimal("100.00");
        Checkout checkout = new Checkout(new CreditCardPayment("4111111111111234"));
        String creditCardResult = checkout.checkout(amount);

        checkout.setPaymentStrategy(new PixPayment("duan@javapatternslab.dev"));
        String pixResult = checkout.checkout(amount);

        assertNotEquals(creditCardResult, pixResult,
                "swapping the strategy at runtime should change Checkout's behavior");
    }
}
