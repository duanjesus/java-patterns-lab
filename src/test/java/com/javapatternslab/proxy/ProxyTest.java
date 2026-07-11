package com.javapatternslab.proxy;

import org.junit.jupiter.api.Test;

import static org.junit.jupiter.api.Assertions.assertEquals;

class ProxyTest {

    @Test
    void repeatedLookupsOfSameIdHitRealCatalogOnlyOnce() {
        RealProductCatalog realCatalog = new RealProductCatalog();
        ProductCatalog proxy = new CachingProductCatalogProxy(realCatalog);

        Product first = proxy.findById("P-1");
        Product second = proxy.findById("P-1");
        Product third = proxy.findById("P-1");

        assertEquals(first, second);
        assertEquals(second, third);
        assertEquals(1, realCatalog.getLookupCount());
    }

    @Test
    void differentIdsEachTriggerARealLookup() {
        RealProductCatalog realCatalog = new RealProductCatalog();
        ProductCatalog proxy = new CachingProductCatalogProxy(realCatalog);

        proxy.findById("P-1");
        proxy.findById("P-2");

        assertEquals(2, realCatalog.getLookupCount());
    }
}
