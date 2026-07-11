package com.javapatternslab.abstractfactory;

public class DomesticShippingDocumentFactory implements ShippingDocumentFactory {

    @Override
    public ShippingLabel createShippingLabel(String orderId) {
        return new DomesticShippingLabel(orderId);
    }

    @Override
    public CustomsForm createCustomsForm(String orderId) {
        return new NotRequiredCustomsForm();
    }
}
