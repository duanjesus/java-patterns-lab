package com.javapatternslab.adapter;

import org.junit.jupiter.api.Test;

import java.math.BigDecimal;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertTrue;

class AdapterTest {

    @Test
    void adapterTranslatesAmountToWholeCentsForLegacyGateway() {
        StringBuilder capturedAmount = new StringBuilder();
        LegacyPaymentGateway legacyGateway = new LegacyPaymentGateway() {
            @Override
            public String submitTransaction(String amountInCents, String currencyCode) {
                capturedAmount.append(amountInCents);
                return "APPROVED";
            }
        };
        PaymentGateway gateway = new LegacyPaymentGatewayAdapter(legacyGateway);

        boolean approved = gateway.charge(new BigDecimal("129.90"));

        assertTrue(approved);
        assertEquals("12990", capturedAmount.toString());
    }

    @Test
    void adapterReturnsFalseWhenLegacyGatewayDeclines() {
        LegacyPaymentGateway legacyGateway = new LegacyPaymentGateway() {
            @Override
            public String submitTransaction(String amountInCents, String currencyCode) {
                return "DECLINED";
            }
        };
        PaymentGateway gateway = new LegacyPaymentGatewayAdapter(legacyGateway);

        boolean approved = gateway.charge(new BigDecimal("50.00"));

        assertEquals(false, approved);
    }
}
