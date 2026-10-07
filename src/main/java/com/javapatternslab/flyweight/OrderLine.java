package com.javapatternslab.flyweight;

import java.math.BigDecimal;

/**
 * The context: holds only what is unique to this line, plus a reference to the shared product data.
 */
public record OrderLine(String orderId, ProductType productType, int quantity) {

    public BigDecimal total() {
        return productType.totalFor(quantity);
    }
}
