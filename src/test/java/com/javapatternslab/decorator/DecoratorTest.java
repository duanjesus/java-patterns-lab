package com.javapatternslab.decorator;

import org.junit.jupiter.api.Test;

import java.math.BigDecimal;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertTrue;

class DecoratorTest {

    @Test
    void singleDecoratorAddsItsOwnCost() {
        Product product = new GiftWrapDecorator(new BasicProduct("Book", new BigDecimal("50.00")));

        assertEquals(0, product.getPrice().compareTo(new BigDecimal("55.00")));
        assertEquals("Book + gift wrap", product.getDescription());
    }

    @Test
    void decoratorsStackInAnyOrder() {
        Product product = new InsuranceDecorator(
                new ExpressShippingDecorator(
                        new GiftWrapDecorator(
                                new BasicProduct("Keyboard", new BigDecimal("350.00")))));

        // 350 + 5 (gift wrap) + 15 (express) = 370, + 2% insurance on 370 = 7.40 -> 377.40
        assertEquals(0, product.getPrice().compareTo(new BigDecimal("377.40")));
        assertTrue(product.getDescription().contains("gift wrap"));
        assertTrue(product.getDescription().contains("express shipping"));
        assertTrue(product.getDescription().contains("insurance"));
    }

    @Test
    void undecoratedProductHasBasePriceOnly() {
        Product product = new BasicProduct("Book", new BigDecimal("50.00"));

        assertEquals(0, product.getPrice().compareTo(new BigDecimal("50.00")));
    }
}
