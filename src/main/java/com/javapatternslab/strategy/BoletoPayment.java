package com.javapatternslab.strategy;

import java.math.BigDecimal;
import java.math.RoundingMode;

public class BoletoPayment implements PaymentStrategy {

    private static final BigDecimal FLAT_FEE = new BigDecimal("2.50");

    @Override
    public String pay(BigDecimal amount) {
        BigDecimal total = amount.add(FLAT_FEE).setScale(2, RoundingMode.HALF_UP);
        return "Generated boleto for R$" + total + " (includes R$" + FLAT_FEE + " flat fee), due in 3 business days";
    }
}
