package com.javapatternslab.proxy;

public class ProxyDemo {

    public static void main(String[] args) {
        System.out.println("-- Proxy: repeated lookups through a caching proxy --");

        RealProductCatalog realCatalog = new RealProductCatalog();
        ProductCatalog proxy = new CachingProductCatalogProxy(realCatalog);

        System.out.println(proxy.findById("P-1"));
        System.out.println(proxy.findById("P-1"));
        System.out.println(proxy.findById("P-1"));

        System.out.println("Real catalog lookup count: " + realCatalog.getLookupCount());
    }
}
