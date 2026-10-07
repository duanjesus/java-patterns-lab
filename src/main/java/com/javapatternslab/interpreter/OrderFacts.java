package com.javapatternslab.interpreter;

import java.math.BigDecimal;

/**
 * The context a rule is interpreted against: the values the rule language's variables refer to.
 */
public record OrderFacts(BigDecimal total, int items) {

    public static boolean isVariable(String name) {
        return name.equals("total") || name.equals("items");
    }

    public BigDecimal valueOf(String variable) {
        return switch (variable) {
            case "total" -> total;
            case "items" -> BigDecimal.valueOf(items);
            default -> throw new IllegalArgumentException("Unknown variable '" + variable + "'");
        };
    }
}
