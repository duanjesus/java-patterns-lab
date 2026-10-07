package com.javapatternslab.visitor;

import java.math.BigDecimal;

public class VisitorDemo {

    public static void main(String[] args) {
        System.out.println("-- Visitor: tax and shipping computed over one cart tree, without touching the item classes --");

        CartBundle peripheralsKit = new CartBundle("Peripherals kit")
                .add(new PhysicalItem("Mechanical keyboard", new BigDecimal("350.00"), new BigDecimal("1.2")))
                .add(new PhysicalItem("Wireless mouse", new BigDecimal("120.00"), new BigDecimal("0.3")));

        CartBundle cart = new CartBundle("Desk starter cart")
                .add(peripheralsKit)
                .add(new DigitalItem("Java patterns e-book", new BigDecimal("40.00")));

        TaxVisitor tax = new TaxVisitor();
        cart.accept(tax);
        System.out.println("Tax (12% physical, 5% digital): R$" + tax.getTotal());

        ShippingVisitor shipping = new ShippingVisitor();
        cart.accept(shipping);
        System.out.println("Shipping (R$4.00/kg + R$5.00 per bundle): R$" + shipping.getTotal());
    }
}
