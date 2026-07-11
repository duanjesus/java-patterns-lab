package com.javapatternslab.composite;

import java.math.BigDecimal;

public class CompositeDemo {

    public static void main(String[] args) {
        System.out.println("-- Composite: individual items and nested bundles share one interface --");

        CartBundle peripheralsKit = new CartBundle("Peripherals kit")
                .add(new CartItem("Mechanical keyboard", new BigDecimal("350.00")))
                .add(new CartItem("Wireless mouse", new BigDecimal("120.00")));

        CartBundle megaKit = new CartBundle("Desk mega kit")
                .add(peripheralsKit)
                .add(new CartItem("27\" monitor", new BigDecimal("1200.00")));

        System.out.println(megaKit.getDescription());
        System.out.println("Total: R$" + megaKit.getPrice());
    }
}
