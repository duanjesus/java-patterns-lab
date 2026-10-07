package com.javapatternslab.interpreter;

public interface RuleExpression {

    boolean interpret(OrderFacts facts);
}
