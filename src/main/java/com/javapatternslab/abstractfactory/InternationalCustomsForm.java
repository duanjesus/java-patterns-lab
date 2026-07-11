package com.javapatternslab.abstractfactory;

public class InternationalCustomsForm implements CustomsForm {

    private final String orderId;

    public InternationalCustomsForm(String orderId) {
        this.orderId = orderId;
    }

    @Override
    public String print() {
        return "[Customs Form] Declaration filed for order " + orderId + " — value declared, duties prepaid";
    }
}
