package com.javapatternslab.mediator;

import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;

import java.math.BigDecimal;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertFalse;
import static org.junit.jupiter.api.Assertions.assertTrue;

class MediatorTest {

    private CartPanel cart;
    private CouponField coupon;
    private ShippingSelector shipping;
    private PlaceOrderButton button;
    private OrderSummary summary;

    @BeforeEach
    void wireTheForm() {
        cart = new CartPanel();
        coupon = new CouponField();
        shipping = new ShippingSelector();
        button = new PlaceOrderButton();
        summary = new OrderSummary();
        new CheckoutForm(cart, coupon, shipping, button, summary);
    }

    @Test
    void changingCartOrShippingUpdatesTheSummaryTotal() {
        cart.setSubtotal(new BigDecimal("300.00"));
        shipping.select(ShippingOption.EXPRESS);

        assertEquals(new BigDecimal("345.00"), summary.getTotal());
    }

    @Test
    void discountCouponTakesTenPercentOffTheSubtotalOnly() {
        cart.setSubtotal(new BigDecimal("300.00"));
        shipping.select(ShippingOption.EXPRESS);

        coupon.enter("DESC10");

        assertEquals(new BigDecimal("30.00"), summary.getDiscount());
        assertEquals(new BigDecimal("45.00"), summary.getShippingCost());
        assertEquals(new BigDecimal("315.00"), summary.getTotal());
    }

    @Test
    void freeShippingCouponZeroesShippingEvenWhenShippingIsChosenAfterTheCoupon() {
        cart.setSubtotal(new BigDecimal("300.00"));
        coupon.enter("fretegratis");

        shipping.select(ShippingOption.EXPRESS);

        assertEquals(0, summary.getShippingCost().signum());
        assertEquals(0, summary.getTotal().compareTo(new BigDecimal("300.00")));
    }

    @Test
    void replacingACouponRemovesThePreviousCouponsEffect() {
        cart.setSubtotal(new BigDecimal("300.00"));
        shipping.select(ShippingOption.STANDARD);
        coupon.enter("DESC10");

        coupon.enter("UNKNOWN-CODE");

        assertEquals(0, summary.getDiscount().signum());
        assertEquals(new BigDecimal("320.00"), summary.getTotal());
    }

    @Test
    void placeOrderButtonIsEnabledOnlyWithItemsInTheCartAndShippingChosen() {
        assertFalse(button.isEnabled());

        cart.setSubtotal(new BigDecimal("300.00"));
        assertFalse(button.isEnabled(), "shipping not chosen yet");

        shipping.select(ShippingOption.PICKUP);
        assertTrue(button.isEnabled());

        cart.setSubtotal(BigDecimal.ZERO);
        assertFalse(button.isEnabled(), "emptying the cart must disable the button again");
    }

    @Test
    void clickPlacesTheOrderOnlyWhenTheButtonIsEnabled() {
        assertFalse(button.click());
        assertFalse(summary.isPlaced());

        cart.setSubtotal(new BigDecimal("300.00"));
        shipping.select(ShippingOption.STANDARD);

        assertTrue(button.click());
        assertTrue(summary.isPlaced());
    }
}
