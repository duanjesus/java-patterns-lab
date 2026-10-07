package com.javapatternslab.interpreter;

public record NotExpression(RuleExpression operand) implements RuleExpression {

    @Override
    public boolean interpret(OrderFacts facts) {
        return !operand.interpret(facts);
    }

    @Override
    public String toString() {
        return "NOT " + operand;
    }
}
