package com.javapatternslab.adapter;

import java.math.BigDecimal;

public class LegacyPaymentGatewayAdapter implements PaymentGateway {

    private final LegacyPaymentGateway legacyGateway;

    public LegacyPaymentGatewayAdapter(LegacyPaymentGateway legacyGateway) {
        this.legacyGateway = legacyGateway;
    }

    @Override
    public boolean charge(BigDecimal amount) {
        String amountInCents = amount.movePointRight(2).toBigInteger().toString();
        String status = legacyGateway.submitTransaction(amountInCents, "BRL");
        return "APPROVED".equals(status);
    }
}
