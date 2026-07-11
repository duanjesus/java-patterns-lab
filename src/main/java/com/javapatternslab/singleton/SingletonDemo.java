package com.javapatternslab.singleton;

public class SingletonDemo {

    public static void main(String[] args) {
        System.out.println("-- Singleton: two getInstance() calls, one shared CheckoutConfig --");

        CheckoutConfig first = CheckoutConfig.getInstance();
        CheckoutConfig second = CheckoutConfig.getInstance();

        System.out.println("Same instance: " + (first == second));
        System.out.println("Tax rate: " + first.getTaxRate() + ", currency: " + first.getDefaultCurrency());
    }
}
