package com.javapatternslab.builder;

import org.junit.jupiter.api.Test;

import java.math.BigDecimal;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertThrows;

class BuilderTest {

    @Test
    void buildsMinimalInvoiceWithRequiredFieldsOnly() {
        Invoice invoice = new InvoiceBuilder()
                .withCustomer("Ada Lovelace")
                .addItem("Consulting hour", new BigDecimal("150.00"))
                .build();

        assertEquals("Ada Lovelace", invoice.getCustomer());
        assertEquals(0, invoice.total().compareTo(new BigDecimal("150.00")));
    }

    @Test
    void discountIsSubtractedFromItemTotal() {
        Invoice invoice = new InvoiceBuilder()
                .withCustomer("Grace Hopper")
                .addItem("Consulting hour", new BigDecimal("150.00"))
                .addItem("Code review", new BigDecimal("80.00"))
                .withDiscount(new BigDecimal("20.00"))
                .build();

        assertEquals(0, invoice.total().compareTo(new BigDecimal("210.00")));
    }

    @Test
    void totalNeverGoesNegativeWhenDiscountExceedsSubtotal() {
        Invoice invoice = new InvoiceBuilder()
                .withCustomer("Ada Lovelace")
                .addItem("Small item", new BigDecimal("10.00"))
                .withDiscount(new BigDecimal("999.00"))
                .build();

        assertEquals(0, invoice.total().compareTo(BigDecimal.ZERO));
    }

    @Test
    void buildFailsWithoutCustomer() {
        InvoiceBuilder builder = new InvoiceBuilder()
                .addItem("Consulting hour", new BigDecimal("150.00"));

        assertThrows(IllegalStateException.class, builder::build);
    }

    @Test
    void buildFailsWithoutLineItems() {
        InvoiceBuilder builder = new InvoiceBuilder().withCustomer("Ada Lovelace");

        assertThrows(IllegalStateException.class, builder::build);
    }
}
