package com.javapatternslab.proxy;

import java.math.BigDecimal;
import java.util.Map;

/**
 * Stands in for an expensive lookup (a real implementation might hit a
 * database or a remote service) — {@code lookupCount} lets tests and the
 * demo prove how many times this class was actually queried.
 */
public class RealProductCatalog implements ProductCatalog {

    private static final Map<String, Product> PRODUCTS = Map.of(
            "P-1", new Product("P-1", "Mechanical keyboard", new BigDecimal("350.00")),
            "P-2", new Product("P-2", "27\" monitor", new BigDecimal("1200.00"))
    );

    private int lookupCount = 0;

    @Override
    public Product findById(String id) {
        lookupCount++;
        System.out.println("[RealProductCatalog] Querying for " + id + " (lookup #" + lookupCount + ")");
        Product product = PRODUCTS.get(id);
        if (product == null) {
            throw new IllegalArgumentException("No such product: " + id);
        }
        return product;
    }

    public int getLookupCount() {
        return lookupCount;
    }
}
