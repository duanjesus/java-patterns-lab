package com.javapatternslab.abstractfactory;

public class NotRequiredCustomsForm implements CustomsForm {

    @Override
    public String print() {
        return "[Customs Form] Not required for domestic shipment";
    }
}
