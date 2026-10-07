package com.javapatternslab.mediator;

import java.math.BigDecimal;

public class CartPanel extends CheckoutComponent {

    private BigDecimal subtotal = BigDecimal.ZERO;

    public void setSubtotal(BigDecimal subtotal) {
        this.subtotal = subtotal;
        notifyMediator(CheckoutEvent.CART_CHANGED);
    }

    public BigDecimal getSubtotal() {
        return subtotal;
    }
}
