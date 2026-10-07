package com.javapatternslab.visitor;

import java.math.BigDecimal;
import java.math.RoundingMode;

public class ShippingVisitor implements CartVisitor {

    private static final BigDecimal RATE_PER_KG = new BigDecimal("4.00");
    private static final BigDecimal BUNDLE_PACKAGING_FEE = new BigDecimal("5.00");

    private BigDecimal total = BigDecimal.ZERO;

    @Override
    public void visit(PhysicalItem item) {
        total = total.add(item.weightKg().multiply(RATE_PER_KG));
    }

    @Override
    public void visit(DigitalItem item) {
        // Delivered by download: nothing to ship.
    }

    @Override
    public void visit(CartBundle bundle) {
        total = total.add(BUNDLE_PACKAGING_FEE);
    }

    public BigDecimal getTotal() {
        return total.setScale(2, RoundingMode.HALF_UP);
    }
}
