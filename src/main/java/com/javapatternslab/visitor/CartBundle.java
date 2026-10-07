package com.javapatternslab.visitor;

import java.util.ArrayList;
import java.util.List;

public class CartBundle implements CartElement {

    private final String name;
    private final List<CartElement> children = new ArrayList<>();

    public CartBundle(String name) {
        this.name = name;
    }

    public CartBundle add(CartElement element) {
        children.add(element);
        return this;
    }

    public String getName() {
        return name;
    }

    @Override
    public void accept(CartVisitor visitor) {
        // The structure owns the traversal: a visitor never has to know how a bundle stores its children.
        visitor.visit(this);
        for (CartElement child : children) {
            child.accept(visitor);
        }
    }
}
