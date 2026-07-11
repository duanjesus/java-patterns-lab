package com.javapatternslab.abstractfactory;

public class InternationalShippingDocumentFactory implements ShippingDocumentFactory {

    @Override
    public ShippingLabel createShippingLabel(String orderId) {
        return new InternationalShippingLabel(orderId);
    }

    @Override
    public CustomsForm createCustomsForm(String orderId) {
        return new InternationalCustomsForm(orderId);
    }
}
