package com.javapatternslab.interpreter;

import java.math.BigDecimal;

public enum ComparisonOperator {
    GREATER_OR_EQUAL(">="),
    GREATER(">"),
    LESS_OR_EQUAL("<="),
    LESS("<"),
    EQUAL("=");

    private final String symbol;

    ComparisonOperator(String symbol) {
        this.symbol = symbol;
    }

    public String symbol() {
        return symbol;
    }

    public boolean test(BigDecimal left, BigDecimal right) {
        int comparison = left.compareTo(right);
        return switch (this) {
            case GREATER_OR_EQUAL -> comparison >= 0;
            case GREATER -> comparison > 0;
            case LESS_OR_EQUAL -> comparison <= 0;
            case LESS -> comparison < 0;
            case EQUAL -> comparison == 0;
        };
    }

    public static ComparisonOperator fromSymbol(String symbol) {
        for (ComparisonOperator operator : values()) {
            if (operator.symbol.equals(symbol)) {
                return operator;
            }
        }
        throw new IllegalArgumentException("Expected a comparison operator but found '" + symbol + "'");
    }
}
