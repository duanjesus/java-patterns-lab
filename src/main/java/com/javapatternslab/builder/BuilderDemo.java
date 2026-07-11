package com.javapatternslab.builder;

import java.math.BigDecimal;

public class BuilderDemo {

    public static void main(String[] args) {
        System.out.println("-- Builder: same InvoiceBuilder, minimal vs fully-loaded invoice --");

        Invoice minimal = new InvoiceBuilder()
                .withCustomer("Ada Lovelace")
                .addItem("Consulting hour", new BigDecimal("150.00"))
                .build();
        System.out.println(minimal);

        Invoice fullyLoaded = new InvoiceBuilder()
                .withCustomer("Grace Hopper")
                .addItem("Consulting hour", new BigDecimal("150.00"))
                .addItem("Code review", new BigDecimal("80.00"))
                .withDiscount(new BigDecimal("20.00"))
                .withNotes("Loyalty discount applied")
                .build();
        System.out.println(fullyLoaded);
    }
}
