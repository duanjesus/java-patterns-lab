package com.javapatternslab.singleton;

import java.math.BigDecimal;

public final class CheckoutConfig {

    private final BigDecimal taxRate;
    private final String defaultCurrency;

    private CheckoutConfig() {
        this.taxRate = new BigDecimal("0.065");
        this.defaultCurrency = "BRL";
    }

    private static final class Holder {
        private static final CheckoutConfig INSTANCE = new CheckoutConfig();
    }

    public static CheckoutConfig getInstance() {
        return Holder.INSTANCE;
    }

    public BigDecimal getTaxRate() {
        return taxRate;
    }

    public String getDefaultCurrency() {
        return defaultCurrency;
    }
}
