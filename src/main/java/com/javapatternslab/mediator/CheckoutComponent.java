package com.javapatternslab.mediator;

/**
 * Base for the checkout's parts. A component knows the mediator and nothing else:
 * it never holds a reference to another component.
 */
public abstract class CheckoutComponent {

    private CheckoutMediator mediator;

    public void setMediator(CheckoutMediator mediator) {
        this.mediator = mediator;
    }

    protected void notifyMediator(CheckoutEvent event) {
        if (mediator != null) {
            mediator.onEvent(event);
        }
    }
}
