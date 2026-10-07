package com.javapatternslab.mediator;

import java.math.BigDecimal;

public enum ShippingOption {
    STANDARD("20.00"),
    EXPRESS("45.00"),
    PICKUP("0.00");

    private final BigDecimal cost;

    ShippingOption(String cost) {
        this.cost = new BigDecimal(cost);
    }

    public BigDecimal cost() {
        return cost;
    }
}
