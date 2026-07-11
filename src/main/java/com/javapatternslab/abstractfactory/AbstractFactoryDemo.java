package com.javapatternslab.abstractfactory;

import java.util.List;

public class AbstractFactoryDemo {

    public static void main(String[] args) {
        System.out.println("-- Abstract Factory: matched label + customs-form pairs, per shipping mode --");

        List<ShippingDocumentFactory> factories = List.of(
                new DomesticShippingDocumentFactory(),
                new InternationalShippingDocumentFactory()
        );

        for (ShippingDocumentFactory factory : factories) {
            ShippingLabel label = factory.createShippingLabel("ORD-3001");
            CustomsForm customsForm = factory.createCustomsForm("ORD-3001");
            System.out.println(label.print());
            System.out.println(customsForm.print());
        }
    }
}
