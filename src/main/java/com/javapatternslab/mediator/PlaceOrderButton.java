package com.javapatternslab.mediator;

public class PlaceOrderButton extends CheckoutComponent {

    private boolean enabled;

    public void setEnabled(boolean enabled) {
        this.enabled = enabled;
    }

    public boolean isEnabled() {
        return enabled;
    }

    /** Returns whether the click was accepted; a disabled button ignores it. */
    public boolean click() {
        if (!enabled) {
            return false;
        }
        notifyMediator(CheckoutEvent.PLACE_ORDER_CLICKED);
        return true;
    }
}
