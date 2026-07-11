package com.javapatternslab.strategy;

import java.math.BigDecimal;

public class StrategyDemo {

    public static void main(String[] args) {
        BigDecimal amount = new BigDecimal("100.00");
        Checkout checkout = new Checkout(new CreditCardPayment("4111111111111234"));

        System.out.println("-- Strategy: same Checkout, three payment strategies --");
        System.out.println(checkout.checkout(amount));

        checkout.setPaymentStrategy(new PixPayment("duan@javapatternslab.dev"));
        System.out.println(checkout.checkout(amount));

        checkout.setPaymentStrategy(new BoletoPayment());
        System.out.println(checkout.checkout(amount));
    }
}
