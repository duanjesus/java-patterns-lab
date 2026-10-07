package com.javapatternslab.visitor;

import org.junit.jupiter.api.Test;

import java.math.BigDecimal;
import java.util.ArrayList;
import java.util.List;

import static org.junit.jupiter.api.Assertions.assertEquals;

class VisitorTest {

    private static final PhysicalItem KEYBOARD =
            new PhysicalItem("Keyboard", new BigDecimal("350.00"), new BigDecimal("1.2"));
    private static final PhysicalItem MOUSE =
            new PhysicalItem("Mouse", new BigDecimal("120.00"), new BigDecimal("0.3"));
    private static final DigitalItem EBOOK = new DigitalItem("E-book", new BigDecimal("40.00"));

    private static CartBundle nestedCart() {
        CartBundle kit = new CartBundle("Kit").add(KEYBOARD).add(MOUSE);
        return new CartBundle("Cart").add(kit).add(EBOOK);
    }

    @Test
    void taxUsesADifferentRatePerElementType() {
        TaxVisitor physical = new TaxVisitor();
        KEYBOARD.accept(physical);

        TaxVisitor digital = new TaxVisitor();
        EBOOK.accept(digital);

        assertEquals(new BigDecimal("42.00"), physical.getTotal());
        assertEquals(new BigDecimal("2.00"), digital.getTotal());
    }

    @Test
    void taxIsAccumulatedAcrossEveryLevelOfANestedBundle() {
        TaxVisitor tax = new TaxVisitor();

        nestedCart().accept(tax);

        assertEquals(new BigDecimal("58.40"), tax.getTotal());
    }

    @Test
    void shippingChargesByWeightSkipsDigitalItemsAndAddsAFeePerBundle() {
        ShippingVisitor shipping = new ShippingVisitor();

        nestedCart().accept(shipping);

        assertEquals(new BigDecimal("16.00"), shipping.getTotal());
    }

    @Test
    void sameTreeGivesDifferentResultsToDifferentVisitors() {
        CartBundle cart = nestedCart();
        TaxVisitor tax = new TaxVisitor();
        ShippingVisitor shipping = new ShippingVisitor();

        cart.accept(tax);
        cart.accept(shipping);

        assertEquals(new BigDecimal("58.40"), tax.getTotal());
        assertEquals(new BigDecimal("16.00"), shipping.getTotal());
    }

    @Test
    void aNewOperationIsJustANewVisitorAndVisitsElementsInTreeOrder() {
        List<String> visited = new ArrayList<>();
        CartVisitor nameCollector = new CartVisitor() {
            @Override
            public void visit(PhysicalItem item) {
                visited.add(item.name());
            }

            @Override
            public void visit(DigitalItem item) {
                visited.add(item.name());
            }

            @Override
            public void visit(CartBundle bundle) {
                visited.add("[" + bundle.getName() + "]");
            }
        };

        nestedCart().accept(nameCollector);

        assertEquals(List.of("[Cart]", "[Kit]", "Keyboard", "Mouse", "E-book"), visited);
    }
}
