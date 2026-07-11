package com.javapatternslab.decorator;

import java.math.BigDecimal;

public class DecoratorDemo {

    public static void main(String[] args) {
        System.out.println("-- Decorator: wrapping a Product one layer at a time --");

        Product product = new BasicProduct("Mechanical keyboard", new BigDecimal("350.00"));
        print(product);

        product = new GiftWrapDecorator(product);
        print(product);

        product = new ExpressShippingDecorator(product);
        print(product);

        product = new InsuranceDecorator(product);
        print(product);
    }

    private static void print(Product product) {
        System.out.println(product.getDescription() + " -> R$" + product.getPrice());
    }
}
