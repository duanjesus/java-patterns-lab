package com.javapatternslab.composite;

import java.math.BigDecimal;

public class CartItem implements CartComponent {

    private final String name;
    private final BigDecimal price;

    public CartItem(String name, BigDecimal price) {
        this.name = name;
        this.price = price;
    }

    @Override
    public BigDecimal getPrice() {
        return price;
    }

    @Override
    public String getDescription() {
        return name;
    }
}
