package com.javapatternslab.builder;

import java.math.BigDecimal;
import java.util.ArrayList;
import java.util.List;

public class InvoiceBuilder {

    private String customer;
    private final List<LineItem> items = new ArrayList<>();
    private BigDecimal discount = BigDecimal.ZERO;
    private String notes = "";

    public InvoiceBuilder withCustomer(String customer) {
        this.customer = customer;
        return this;
    }

    public InvoiceBuilder addItem(String description, BigDecimal price) {
        this.items.add(new LineItem(description, price));
        return this;
    }

    public InvoiceBuilder withDiscount(BigDecimal discount) {
        this.discount = discount;
        return this;
    }

    public InvoiceBuilder withNotes(String notes) {
        this.notes = notes;
        return this;
    }

    public Invoice build() {
        if (customer == null || customer.isBlank()) {
            throw new IllegalStateException("customer is required");
        }
        if (items.isEmpty()) {
            throw new IllegalStateException("at least one line item is required");
        }
        return new Invoice(customer, items, discount, notes);
    }
}
