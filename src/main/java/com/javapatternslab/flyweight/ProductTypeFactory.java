package com.javapatternslab.flyweight;

import java.math.BigDecimal;
import java.util.HashMap;
import java.util.Map;

public class ProductTypeFactory {

    private final Map<String, ProductType> pool = new HashMap<>();

    /**
     * Returns the shared instance for a SKU, creating it on first request.
     * The first definition seen for a SKU is the one that is kept.
     */
    public ProductType get(String sku, String name, String category, BigDecimal unitPrice, BigDecimal taxRate) {
        return pool.computeIfAbsent(sku, key -> new ProductType(key, name, category, unitPrice, taxRate));
    }

    public int poolSize() {
        return pool.size();
    }
}
