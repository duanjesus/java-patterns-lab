package com.javapatternslab.decorator;

import java.math.BigDecimal;

public class GiftWrapDecorator extends ProductDecorator {

    private static final BigDecimal GIFT_WRAP_COST = new BigDecimal("5.00");

    public GiftWrapDecorator(Product wrapped) {
        super(wrapped);
    }

    @Override
    public BigDecimal getPrice() {
        return wrapped.getPrice().add(GIFT_WRAP_COST);
    }

    @Override
    public String getDescription() {
        return wrapped.getDescription() + " + gift wrap";
    }
}
