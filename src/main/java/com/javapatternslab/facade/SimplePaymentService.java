package com.javapatternslab.facade;

import java.math.BigDecimal;

public class SimplePaymentService implements PaymentService {

    @Override
    public boolean charge(String orderId, BigDecimal amount) {
        System.out.println("[Payment] Charged R$" + amount + " for " + orderId);
        return true;
    }
}
