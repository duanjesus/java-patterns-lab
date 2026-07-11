package com.javapatternslab.composite;

import org.junit.jupiter.api.Test;

import java.math.BigDecimal;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertTrue;

class CompositeTest {

    @Test
    void leafPriceIsItsOwnPrice() {
        CartComponent item = new CartItem("Book", new BigDecimal("50.00"));

        assertEquals(0, item.getPrice().compareTo(new BigDecimal("50.00")));
    }

    @Test
    void bundleSumsItsChildren() {
        CartBundle bundle = new CartBundle("Starter kit")
                .add(new CartItem("Keyboard", new BigDecimal("350.00")))
                .add(new CartItem("Mouse", new BigDecimal("120.00")));

        assertEquals(0, bundle.getPrice().compareTo(new BigDecimal("470.00")));
        assertTrue(bundle.getDescription().contains("Keyboard"));
        assertTrue(bundle.getDescription().contains("Mouse"));
    }

    @Test
    void nestedBundleSumsRecursively() {
        CartBundle inner = new CartBundle("Inner kit")
                .add(new CartItem("Keyboard", new BigDecimal("350.00")))
                .add(new CartItem("Mouse", new BigDecimal("120.00")));

        CartBundle outer = new CartBundle("Mega kit")
                .add(inner)
                .add(new CartItem("Monitor", new BigDecimal("1200.00")));

        assertEquals(0, outer.getPrice().compareTo(new BigDecimal("1670.00")));
    }
}
