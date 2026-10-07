package com.javapatternslab.prototype;

import java.math.BigDecimal;

public class PrototypeDemo {

    public static void main(String[] args) {
        System.out.println("-- Prototype: new orders are copied from a registered template --");

        OrderTemplateRegistry registry = new OrderTemplateRegistry();
        registry.register("monthly-coffee",
                new OrderTemplate("Monthly coffee kit", new ShippingAddress("Rua das Flores, 100", "Salvador"))
                        .addLine("COFFEE-1KG", 2, new BigDecimal("48.00"))
                        .addLine("FILTER-100", 1, new BigDecimal("12.00")));

        OrderTemplate january = registry.create("monthly-coffee");

        OrderTemplate february = registry.create("monthly-coffee");
        february.rename("Monthly coffee kit + mug");
        february.addLine("MUG-350ML", 1, new BigDecimal("35.00"));
        february.getShippingAddress().setStreet("Av. Sete de Setembro, 500");

        print("January ", january);
        print("February", february);
        print("Template", registry.create("monthly-coffee"));
    }

    private static void print(String label, OrderTemplate order) {
        System.out.println(label + ": " + order.getName() + " | " + order.getLines().size() + " lines | R$"
                + order.getTotal() + " | ships to " + order.getShippingAddress());
    }
}
