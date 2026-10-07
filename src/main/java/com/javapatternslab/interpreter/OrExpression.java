package com.javapatternslab.interpreter;

public record OrExpression(RuleExpression left, RuleExpression right) implements RuleExpression {

    @Override
    public boolean interpret(OrderFacts facts) {
        return left.interpret(facts) || right.interpret(facts);
    }

    @Override
    public String toString() {
        return "(" + left + " OR " + right + ")";
    }
}
