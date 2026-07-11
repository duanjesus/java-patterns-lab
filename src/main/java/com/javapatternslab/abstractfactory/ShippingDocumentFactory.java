package com.javapatternslab.abstractfactory;

public interface ShippingDocumentFactory {

    ShippingLabel createShippingLabel(String orderId);

    CustomsForm createCustomsForm(String orderId);
}
