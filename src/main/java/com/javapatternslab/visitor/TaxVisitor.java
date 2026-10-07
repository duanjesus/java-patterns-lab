package com.javapatternslab.visitor;

import java.math.BigDecimal;
import java.math.RoundingMode;

public class TaxVisitor implements CartVisitor {

    private static final BigDecimal PHYSICAL_RATE = new BigDecimal("0.12");
    private static final BigDecimal DIGITAL_RATE = new BigDecimal("0.05");

    private BigDecimal total = BigDecimal.ZERO;

    @Override
    public void visit(PhysicalItem item) {
        total = total.add(item.price().multiply(PHYSICAL_RATE));
    }

    @Override
    public void visit(DigitalItem item) {
        total = total.add(item.price().multiply(DIGITAL_RATE));
    }

    @Override
    public void visit(CartBundle bundle) {
        // A bundle has no tax of its own; its children are taxed individually.
    }

    public BigDecimal getTotal() {
        return total.setScale(2, RoundingMode.HALF_UP);
    }
}
