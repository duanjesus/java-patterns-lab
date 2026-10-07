package com.javapatternslab.mediator;

import java.math.BigDecimal;
import java.math.RoundingMode;

/**
 * The concrete mediator: every rule that involves more than one component lives here.
 */
public class CheckoutForm implements CheckoutMediator {

    private static final BigDecimal DESC10_RATE = new BigDecimal("0.10");

    private final CartPanel cart;
    private final CouponField couponField;
    private final ShippingSelector shipping;
    private final PlaceOrderButton placeOrderButton;
    private final OrderSummary summary;

    public CheckoutForm(CartPanel cart, CouponField couponField, ShippingSelector shipping,
                        PlaceOrderButton placeOrderButton, OrderSummary summary) {
        this.cart = cart;
        this.couponField = couponField;
        this.shipping = shipping;
        this.placeOrderButton = placeOrderButton;
        this.summary = summary;

        cart.setMediator(this);
        couponField.setMediator(this);
        shipping.setMediator(this);
        placeOrderButton.setMediator(this);
        recalculate();
    }

    @Override
    public void onEvent(CheckoutEvent event) {
        switch (event) {
            case CART_CHANGED, COUPON_ENTERED, SHIPPING_SELECTED -> recalculate();
            case PLACE_ORDER_CLICKED -> summary.markPlaced();
        }
    }

    private void recalculate() {
        BigDecimal subtotal = cart.getSubtotal();
        Coupon coupon = couponField.getCoupon();

        BigDecimal discount = coupon == Coupon.DESC10
                ? subtotal.multiply(DESC10_RATE).setScale(2, RoundingMode.HALF_UP)
                : BigDecimal.ZERO;
        BigDecimal shippingCost = coupon == Coupon.FRETEGRATIS ? BigDecimal.ZERO : shipping.getCost();

        summary.update(subtotal, discount, shippingCost);
        placeOrderButton.setEnabled(subtotal.signum() > 0 && shipping.hasSelection());
    }
}
