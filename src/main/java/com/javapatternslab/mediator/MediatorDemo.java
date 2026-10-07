package com.javapatternslab.mediator;

import java.math.BigDecimal;

public class MediatorDemo {

    public static void main(String[] args) {
        System.out.println("-- Mediator: checkout components never talk to each other, only to the form --");

        CartPanel cart = new CartPanel();
        CouponField coupon = new CouponField();
        ShippingSelector shipping = new ShippingSelector();
        PlaceOrderButton button = new PlaceOrderButton();
        OrderSummary summary = new OrderSummary();
        new CheckoutForm(cart, coupon, shipping, button, summary);

        print("Empty form", summary, button);
        System.out.println("Click on disabled button accepted? " + button.click());

        cart.setSubtotal(new BigDecimal("300.00"));
        print("Cart set to R$300.00", summary, button);

        shipping.select(ShippingOption.EXPRESS);
        print("Express shipping selected", summary, button);

        coupon.enter("DESC10");
        print("Coupon DESC10 entered", summary, button);

        coupon.enter("FRETEGRATIS");
        print("Coupon FRETEGRATIS entered", summary, button);

        System.out.println("Click accepted? " + button.click());
        print("After click", summary, button);
    }

    private static void print(String step, OrderSummary summary, PlaceOrderButton button) {
        System.out.println(step + " -> " + summary.describe()
                + " | button " + (button.isEnabled() ? "enabled" : "disabled"));
    }
}
