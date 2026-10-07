package com.javapatternslab.visitor;

import java.math.BigDecimal;

public record DigitalItem(String name, BigDecimal price) implements CartElement {

    @Override
    public void accept(CartVisitor visitor) {
        visitor.visit(this);
    }
}
