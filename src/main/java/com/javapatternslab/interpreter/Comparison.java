package com.javapatternslab.interpreter;

import java.math.BigDecimal;

/**
 * Terminal expression: a leaf of the rule tree, e.g. {@code total >= 300}.
 */
public record Comparison(String variable, ComparisonOperator operator, BigDecimal value) implements RuleExpression {

    @Override
    public boolean interpret(OrderFacts facts) {
        return operator.test(facts.valueOf(variable), value);
    }

    @Override
    public String toString() {
        return variable + " " + operator.symbol() + " " + value;
    }
}
