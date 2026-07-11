package com.javapatternslab.builder;

import java.math.BigDecimal;
import java.util.List;

public class Invoice {

    private final String customer;
    private final List<LineItem> items;
    private final BigDecimal discount;
    private final String notes;

    Invoice(String customer, List<LineItem> items, BigDecimal discount, String notes) {
        this.customer = customer;
        this.items = List.copyOf(items);
        this.discount = discount;
        this.notes = notes;
    }

    public BigDecimal total() {
        BigDecimal subtotal = items.stream()
                .map(LineItem::price)
                .reduce(BigDecimal.ZERO, BigDecimal::add);
        return subtotal.subtract(discount).max(BigDecimal.ZERO);
    }

    public String getCustomer() {
        return customer;
    }

    public List<LineItem> getItems() {
        return items;
    }

    public BigDecimal getDiscount() {
        return discount;
    }

    public String getNotes() {
        return notes;
    }

    @Override
    public String toString() {
        return "Invoice for " + customer + ": " + items.size() + " item(s), total=" + total()
                + (notes.isEmpty() ? "" : ", notes=\"" + notes + "\"");
    }
}
