package com.javapatternslab.flyweight;

import java.math.BigDecimal;
import java.math.RoundingMode;

/**
 * The flyweight: everything about a product that is the same on every order line.
 * The quantity is extrinsic, so it is passed in rather than stored.
 */
public record ProductType(String sku, String name, String category, BigDecimal unitPrice, BigDecimal taxRate) {

    public BigDecimal totalFor(int quantity) {
        BigDecimal net = unitPrice.multiply(BigDecimal.valueOf(quantity));
        return net.add(net.multiply(taxRate)).setScale(2, RoundingMode.HALF_UP);
    }
}
