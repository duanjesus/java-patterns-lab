package com.javapatternslab.adapter;

import java.math.BigDecimal;

public class AdapterDemo {

    public static void main(String[] args) {
        PaymentGateway gateway = new LegacyPaymentGatewayAdapter(new LegacyPaymentGateway());

        System.out.println("-- Adapter: modern PaymentGateway.charge() backed by a legacy client --");
        boolean approved = gateway.charge(new BigDecimal("129.90"));
        System.out.println("Charge approved: " + approved);
    }
}
