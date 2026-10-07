package com.javapatternslab.visitor;

public interface CartElement {

    void accept(CartVisitor visitor);
}
