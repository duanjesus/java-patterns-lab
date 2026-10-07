package com.javapatternslab.memento;

import java.math.BigDecimal;

public class MementoDemo {

    public static void main(String[] args) {
        System.out.println("-- Memento: checkpoint an order draft and roll it back --");

        OrderDraft draft = new OrderDraft();
        DraftHistory history = new DraftHistory();

        draft.addItem("Mechanical keyboard", new BigDecimal("350.00"));
        draft.setShippingAddress("Rua das Flores, 100");
        print("Initial draft", draft);

        history.checkpoint(draft);
        draft.addItem("Wireless mouse", new BigDecimal("120.00"));
        draft.applyCoupon("DESC10");
        print("Added mouse + coupon", draft);

        history.checkpoint(draft);
        draft.removeItem("Mechanical keyboard");
        draft.setShippingAddress("Av. Sete de Setembro, 500");
        print("Removed keyboard, new address", draft);

        history.undo(draft);
        print("Undo #1", draft);

        history.undo(draft);
        print("Undo #2", draft);

        System.out.println("Anything left to undo? " + history.undo(draft));
    }

    private static void print(String step, OrderDraft draft) {
        String coupon = draft.getCouponCode().isEmpty() ? "none" : draft.getCouponCode();
        System.out.println(step + " -> " + draft.getItems().size() + " item(s), R$" + draft.getTotal()
                + ", coupon " + coupon + ", ships to " + draft.getShippingAddress());
    }
}
