package com.javapatternslab.visitor;

import java.math.BigDecimal;

public record PhysicalItem(String name, BigDecimal price, BigDecimal weightKg) implements CartElement {

    @Override
    public void accept(CartVisitor visitor) {
        visitor.visit(this);
    }
}
