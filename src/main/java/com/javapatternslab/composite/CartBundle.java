package com.javapatternslab.composite;

import java.math.BigDecimal;
import java.util.ArrayList;
import java.util.List;
import java.util.stream.Collectors;

public class CartBundle implements CartComponent {

    private final String name;
    private final List<CartComponent> children = new ArrayList<>();

    public CartBundle(String name) {
        this.name = name;
    }

    public CartBundle add(CartComponent component) {
        children.add(component);
        return this;
    }

    @Override
    public BigDecimal getPrice() {
        return children.stream()
                .map(CartComponent::getPrice)
                .reduce(BigDecimal.ZERO, BigDecimal::add);
    }

    @Override
    public String getDescription() {
        String childDescriptions = children.stream()
                .map(CartComponent::getDescription)
                .collect(Collectors.joining(" + "));
        return name + " [" + childDescriptions + "]";
    }
}
