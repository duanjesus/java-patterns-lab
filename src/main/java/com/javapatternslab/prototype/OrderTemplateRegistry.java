package com.javapatternslab.prototype;

import java.util.HashMap;
import java.util.Map;

public class OrderTemplateRegistry {

    private final Map<String, OrderTemplate> templates = new HashMap<>();

    public void register(String key, OrderTemplate template) {
        templates.put(key, template.copy());
    }

    public OrderTemplate create(String key) {
        OrderTemplate template = templates.get(key);
        if (template == null) {
            throw new IllegalArgumentException("No order template registered as '" + key + "'");
        }
        return template.copy();
    }
}
