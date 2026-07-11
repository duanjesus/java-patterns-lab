package com.javapatternslab.decorator;

import java.math.BigDecimal;
import java.math.RoundingMode;

public class InsuranceDecorator extends ProductDecorator {

    private static final BigDecimal INSURANCE_RATE = new BigDecimal("0.02");

    public InsuranceDecorator(Product wrapped) {
        super(wrapped);
    }

    @Override
    public BigDecimal getPrice() {
        BigDecimal insuranceCost = wrapped.getPrice().multiply(INSURANCE_RATE).setScale(2, RoundingMode.HALF_UP);
        return wrapped.getPrice().add(insuranceCost);
    }

    @Override
    public String getDescription() {
        return wrapped.getDescription() + " + insurance";
    }
}
