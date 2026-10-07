package com.javapatternslab.mediator;

import java.math.BigDecimal;
import java.math.RoundingMode;

/**
 * A passive component: it only displays what the mediator tells it and never raises events.
 */
public class OrderSummary {

    private BigDecimal discount = BigDecimal.ZERO;
    private BigDecimal shippingCost = BigDecimal.ZERO;
    private BigDecimal total = BigDecimal.ZERO;
    private boolean placed;

    public void update(BigDecimal subtotal, BigDecimal discount, BigDecimal shippingCost) {
        this.discount = money(discount);
        this.shippingCost = money(shippingCost);
        this.total = money(subtotal.subtract(discount).add(shippingCost));
    }

    private static BigDecimal money(BigDecimal amount) {
        return amount.setScale(2, RoundingMode.HALF_UP);
    }

    public void markPlaced() {
        this.placed = true;
    }

    public BigDecimal getDiscount() {
        return discount;
    }

    public BigDecimal getShippingCost() {
        return shippingCost;
    }

    public BigDecimal getTotal() {
        return total;
    }

    public boolean isPlaced() {
        return placed;
    }

    public String describe() {
        return "discount R$" + discount + " | shipping R$" + shippingCost + " | total R$" + total
                + (placed ? " | ORDER PLACED" : "");
    }
}
