package com.javapatternslab.interpreter;

public record AndExpression(RuleExpression left, RuleExpression right) implements RuleExpression {

    @Override
    public boolean interpret(OrderFacts facts) {
        return left.interpret(facts) && right.interpret(facts);
    }

    @Override
    public String toString() {
        return "(" + left + " AND " + right + ")";
    }
}
