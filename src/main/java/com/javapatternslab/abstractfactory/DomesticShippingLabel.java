package com.javapatternslab.abstractfactory;

public class DomesticShippingLabel implements ShippingLabel {

    private final String orderId;

    public DomesticShippingLabel(String orderId) {
        this.orderId = orderId;
    }

    @Override
    public String print() {
        return "[Domestic Label] Order " + orderId + " — ground shipping, no customs required";
    }
}
