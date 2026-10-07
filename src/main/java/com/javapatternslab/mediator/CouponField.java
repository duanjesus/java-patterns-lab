package com.javapatternslab.mediator;

public class CouponField extends CheckoutComponent {

    private Coupon coupon = Coupon.NONE;

    public void enter(String code) {
        this.coupon = Coupon.fromCode(code);
        notifyMediator(CheckoutEvent.COUPON_ENTERED);
    }

    public Coupon getCoupon() {
        return coupon;
    }
}
