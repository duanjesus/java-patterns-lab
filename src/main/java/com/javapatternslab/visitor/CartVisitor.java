package com.javapatternslab.visitor;

public interface CartVisitor {

    void visit(PhysicalItem item);

    void visit(DigitalItem item);

    void visit(CartBundle bundle);
}
