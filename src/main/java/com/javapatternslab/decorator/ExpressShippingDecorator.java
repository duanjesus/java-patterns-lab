package com.javapatternslab.decorator;

import java.math.BigDecimal;

public class ExpressShippingDecorator extends ProductDecorator {

    private static final BigDecimal EXPRESS_SHIPPING_COST = new BigDecimal("15.00");

    public ExpressShippingDecorator(Product wrapped) {
        super(wrapped);
    }

    @Override
    public BigDecimal getPrice() {
        return wrapped.getPrice().add(EXPRESS_SHIPPING_COST);
    }

    @Override
    public String getDescription() {
        return wrapped.getDescription() + " + express shipping";
    }
}
