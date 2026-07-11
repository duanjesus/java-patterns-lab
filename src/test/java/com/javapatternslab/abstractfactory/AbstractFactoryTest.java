package com.javapatternslab.abstractfactory;

import org.junit.jupiter.api.Test;

import static org.junit.jupiter.api.Assertions.assertInstanceOf;
import static org.junit.jupiter.api.Assertions.assertTrue;

class AbstractFactoryTest {

    @Test
    void domesticFactoryProducesDomesticLabelAndNoCustomsForm() {
        ShippingDocumentFactory factory = new DomesticShippingDocumentFactory();

        ShippingLabel label = factory.createShippingLabel("ORD-1");
        CustomsForm customsForm = factory.createCustomsForm("ORD-1");

        assertInstanceOf(DomesticShippingLabel.class, label);
        assertInstanceOf(NotRequiredCustomsForm.class, customsForm);
        assertTrue(customsForm.print().contains("Not required"));
    }

    @Test
    void internationalFactoryProducesInternationalLabelAndRealCustomsForm() {
        ShippingDocumentFactory factory = new InternationalShippingDocumentFactory();

        ShippingLabel label = factory.createShippingLabel("ORD-2");
        CustomsForm customsForm = factory.createCustomsForm("ORD-2");

        assertInstanceOf(InternationalShippingLabel.class, label);
        assertInstanceOf(InternationalCustomsForm.class, customsForm);
        assertTrue(customsForm.print().contains("ORD-2"));
    }
}
