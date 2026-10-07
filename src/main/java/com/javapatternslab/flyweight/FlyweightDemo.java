package com.javapatternslab.flyweight;

import java.math.BigDecimal;
import java.util.ArrayList;
import java.util.List;

public class FlyweightDemo {

    public static void main(String[] args) {
        System.out.println("-- Flyweight: thousands of order lines share a handful of product objects --");

        ProductTypeFactory factory = new ProductTypeFactory();
        List<OrderLine> lines = new ArrayList<>();

        for (int order = 1; order <= 3000; order++) {
            String orderId = "ORD-" + order;
            lines.add(new OrderLine(orderId, factory.get("KEYBOARD-01", "Mechanical keyboard", "Peripherals",
                    new BigDecimal("350.00"), new BigDecimal("0.12")), 1));
            lines.add(new OrderLine(orderId, factory.get("MOUSE-07", "Wireless mouse", "Peripherals",
                    new BigDecimal("120.00"), new BigDecimal("0.12")), 2));
            lines.add(new OrderLine(orderId, factory.get("EBOOK-33", "Java patterns e-book", "Digital",
                    new BigDecimal("40.00"), new BigDecimal("0.05")), 1));
        }

        BigDecimal grandTotal = lines.stream()
                .map(OrderLine::total)
                .reduce(BigDecimal.ZERO, BigDecimal::add);

        System.out.println("Order lines created: " + lines.size());
        System.out.println("ProductType objects in memory: " + factory.poolSize());
        System.out.println("First line: " + lines.get(0).productType().name() + " x" + lines.get(0).quantity()
                + " = R$" + lines.get(0).total());
        System.out.println("Grand total with tax: R$" + grandTotal);
    }
}
