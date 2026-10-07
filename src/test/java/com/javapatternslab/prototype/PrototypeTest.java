package com.javapatternslab.prototype;

import org.junit.jupiter.api.Test;

import java.math.BigDecimal;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertNotSame;
import static org.junit.jupiter.api.Assertions.assertThrows;

class PrototypeTest {

    private static OrderTemplate coffeeKit() {
        return new OrderTemplate("Coffee kit", new ShippingAddress("Rua A, 1", "Salvador"))
                .addLine("COFFEE-1KG", 2, new BigDecimal("48.00"))
                .addLine("FILTER-100", 1, new BigDecimal("12.00"));
    }

    @Test
    void copyHasSameContentButIsADifferentObject() {
        OrderTemplate original = coffeeKit();

        OrderTemplate copy = original.copy();

        assertNotSame(original, copy);
        assertEquals(original.getName(), copy.getName());
        assertEquals(original.getLines(), copy.getLines());
        assertEquals(0, copy.getTotal().compareTo(new BigDecimal("108.00")));
    }

    @Test
    void addingALineToTheCopyLeavesTheOriginalUntouched() {
        OrderTemplate original = coffeeKit();
        OrderTemplate copy = original.copy();

        copy.addLine("MUG-350ML", 1, new BigDecimal("35.00"));

        assertEquals(3, copy.getLines().size());
        assertEquals(2, original.getLines().size());
        assertEquals(0, original.getTotal().compareTo(new BigDecimal("108.00")));
    }

    @Test
    void copyIsDeepSoChangingItsAddressDoesNotChangeTheOriginals() {
        OrderTemplate original = coffeeKit();
        OrderTemplate copy = original.copy();

        copy.getShippingAddress().setStreet("Av. B, 2");

        assertNotSame(original.getShippingAddress(), copy.getShippingAddress());
        assertEquals("Rua A, 1", original.getShippingAddress().getStreet());
        assertEquals("Av. B, 2", copy.getShippingAddress().getStreet());
    }

    @Test
    void registryHandsOutIndependentCopiesOnEveryCall() {
        OrderTemplateRegistry registry = new OrderTemplateRegistry();
        registry.register("coffee", coffeeKit());

        OrderTemplate first = registry.create("coffee");
        first.addLine("MUG-350ML", 1, new BigDecimal("35.00"));
        OrderTemplate second = registry.create("coffee");

        assertNotSame(first, second);
        assertEquals(2, second.getLines().size(), "changes to one created order must not leak into the template");
    }

    @Test
    void registryIsNotAffectedByLaterChangesToTheObjectThatWasRegistered() {
        OrderTemplateRegistry registry = new OrderTemplateRegistry();
        OrderTemplate source = coffeeKit();
        registry.register("coffee", source);

        source.addLine("MUG-350ML", 1, new BigDecimal("35.00"));

        assertEquals(2, registry.create("coffee").getLines().size());
    }

    @Test
    void unknownTemplateKeyIsRejected() {
        OrderTemplateRegistry registry = new OrderTemplateRegistry();

        assertThrows(IllegalArgumentException.class, () -> registry.create("missing"));
    }
}
