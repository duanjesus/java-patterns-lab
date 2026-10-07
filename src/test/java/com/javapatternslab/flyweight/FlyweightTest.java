package com.javapatternslab.flyweight;

import org.junit.jupiter.api.Test;

import java.math.BigDecimal;
import java.util.ArrayList;
import java.util.Collections;
import java.util.IdentityHashMap;
import java.util.List;
import java.util.Set;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertNotSame;
import static org.junit.jupiter.api.Assertions.assertSame;

class FlyweightTest {

    private static ProductType keyboard(ProductTypeFactory factory) {
        return factory.get("KEYBOARD-01", "Mechanical keyboard", "Peripherals",
                new BigDecimal("350.00"), new BigDecimal("0.12"));
    }

    private static ProductType ebook(ProductTypeFactory factory) {
        return factory.get("EBOOK-33", "Java patterns e-book", "Digital",
                new BigDecimal("40.00"), new BigDecimal("0.05"));
    }

    @Test
    void sameSkuAlwaysReturnsTheSameInstance() {
        ProductTypeFactory factory = new ProductTypeFactory();

        assertSame(keyboard(factory), keyboard(factory));
        assertEquals(1, factory.poolSize());
    }

    @Test
    void differentSkusGetDifferentInstances() {
        ProductTypeFactory factory = new ProductTypeFactory();

        assertNotSame(keyboard(factory), ebook(factory));
        assertEquals(2, factory.poolSize());
    }

    @Test
    void thousandsOfLinesShareOneObjectPerProduct() {
        ProductTypeFactory factory = new ProductTypeFactory();
        List<OrderLine> lines = new ArrayList<>();
        for (int order = 1; order <= 5000; order++) {
            lines.add(new OrderLine("ORD-" + order, keyboard(factory), 1));
            lines.add(new OrderLine("ORD-" + order, ebook(factory), 2));
        }

        Set<ProductType> distinctInstances = Collections.newSetFromMap(new IdentityHashMap<>());
        lines.forEach(line -> distinctInstances.add(line.productType()));

        assertEquals(10000, lines.size());
        assertEquals(2, distinctInstances.size());
        assertEquals(2, factory.poolSize());
    }

    @Test
    void linesSharingAProductStillComputeTheirOwnTotalFromTheirOwnQuantity() {
        ProductTypeFactory factory = new ProductTypeFactory();
        OrderLine one = new OrderLine("ORD-1", keyboard(factory), 1);
        OrderLine three = new OrderLine("ORD-2", keyboard(factory), 3);

        assertSame(one.productType(), three.productType());
        assertEquals(new BigDecimal("392.00"), one.total());
        assertEquals(new BigDecimal("1176.00"), three.total());
    }

    @Test
    void taxRateComesFromTheSharedProductData() {
        ProductTypeFactory factory = new ProductTypeFactory();

        assertEquals(new BigDecimal("84.00"), new OrderLine("ORD-1", ebook(factory), 2).total());
    }
}
