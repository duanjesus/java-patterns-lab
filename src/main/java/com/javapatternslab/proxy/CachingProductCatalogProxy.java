package com.javapatternslab.proxy;

import java.util.HashMap;
import java.util.Map;

public class CachingProductCatalogProxy implements ProductCatalog {

    private final RealProductCatalog realCatalog;
    private final Map<String, Product> cache = new HashMap<>();

    public CachingProductCatalogProxy(RealProductCatalog realCatalog) {
        this.realCatalog = realCatalog;
    }

    @Override
    public Product findById(String id) {
        Product cached = cache.get(id);
        if (cached != null) {
            System.out.println("[CachingProductCatalogProxy] Cache hit for " + id);
            return cached;
        }
        Product product = realCatalog.findById(id);
        cache.put(id, product);
        return product;
    }
}
