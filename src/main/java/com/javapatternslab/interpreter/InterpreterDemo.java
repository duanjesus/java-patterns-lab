package com.javapatternslab.interpreter;

import java.math.BigDecimal;
import java.util.List;

public class InterpreterDemo {

    public static void main(String[] args) {
        System.out.println("-- Interpreter: promotion rules written as text, parsed once, evaluated per order --");

        List<String> rules = List.of(
                "total >= 300 AND items >= 2",
                "total >= 1000 OR items >= 5 AND total >= 200",
                "NOT (total < 100 OR items = 1)");

        List<OrderFacts> orders = List.of(
                new OrderFacts(new BigDecimal("350.00"), 3),
                new OrderFacts(new BigDecimal("1200.00"), 1),
                new OrderFacts(new BigDecimal("80.00"), 6));

        for (String text : rules) {
            RuleExpression rule = RuleParser.parse(text);
            System.out.println();
            System.out.println("Rule text:   " + text);
            System.out.println("Parsed tree: " + rule);
            for (OrderFacts order : orders) {
                System.out.println("  order of R$" + order.total() + " with " + order.items() + " item(s) -> "
                        + (rule.interpret(order) ? "eligible" : "not eligible"));
            }
        }
    }
}
