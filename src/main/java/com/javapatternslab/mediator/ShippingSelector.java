package com.javapatternslab.mediator;

import java.math.BigDecimal;

public class ShippingSelector extends CheckoutComponent {

    private ShippingOption selected;

    public void select(ShippingOption option) {
        this.selected = option;
        notifyMediator(CheckoutEvent.SHIPPING_SELECTED);
    }

    public boolean hasSelection() {
        return selected != null;
    }

    public BigDecimal getCost() {
        return selected == null ? BigDecimal.ZERO : selected.cost();
    }
}
