package com.javapatternslab.decorator;

import java.math.BigDecimal;

public class BasicProduct implements Product {

    private final String name;
    private final BigDecimal price;

    public BasicProduct(String name, BigDecimal price) {
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
