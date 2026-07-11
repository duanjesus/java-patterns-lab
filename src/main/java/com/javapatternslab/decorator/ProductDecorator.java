package com.javapatternslab.decorator;

import java.math.BigDecimal;

public abstract class ProductDecorator implements Product {

    protected final Product wrapped;

    protected ProductDecorator(Product wrapped) {
        this.wrapped = wrapped;
    }

    @Override
    public BigDecimal getPrice() {
        return wrapped.getPrice();
    }

    @Override
    public String getDescription() {
        return wrapped.getDescription();
    }
}
