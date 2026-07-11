package com.javapatternslab.abstractfactory;

public class InternationalShippingLabel implements ShippingLabel {

    private final String orderId;

    public InternationalShippingLabel(String orderId) {
        this.orderId = orderId;
    }

    @Override
    public String print() {
        return "[International Label] Order " + orderId + " — air freight, customs code HS-621149";
    }
}
