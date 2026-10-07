package com.javapatternslab.interpreter;

import org.junit.jupiter.api.Test;

import java.math.BigDecimal;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertFalse;
import static org.junit.jupiter.api.Assertions.assertThrows;
import static org.junit.jupiter.api.Assertions.assertTrue;

class InterpreterTest {

    private static OrderFacts order(String total, int items) {
        return new OrderFacts(new BigDecimal(total), items);
    }

    @Test
    void comparisonReadsItsVariableFromTheOrderBeingInterpreted() {
        RuleExpression rule = new Comparison("total", ComparisonOperator.GREATER_OR_EQUAL, new BigDecimal("300"));

        assertTrue(rule.interpret(order("300.00", 1)));
        assertFalse(rule.interpret(order("299.99", 1)));
    }

    @Test
    void expressionTreeBuiltByHandCombinesItsBranches() {
        RuleExpression bigOrder = new Comparison("total", ComparisonOperator.GREATER_OR_EQUAL, new BigDecimal("300"));
        RuleExpression manyItems = new Comparison("items", ComparisonOperator.GREATER, new BigDecimal("2"));

        assertFalse(new AndExpression(bigOrder, manyItems).interpret(order("500.00", 2)));
        assertTrue(new OrExpression(bigOrder, manyItems).interpret(order("500.00", 2)));
        assertTrue(new NotExpression(manyItems).interpret(order("500.00", 2)));
    }

    @Test
    void parserBuildsTheSameTreeAsBuildingItByHand() {
        RuleExpression expected = new AndExpression(
                new Comparison("total", ComparisonOperator.GREATER_OR_EQUAL, new BigDecimal("300")),
                new Comparison("items", ComparisonOperator.GREATER_OR_EQUAL, new BigDecimal("2")));

        assertEquals(expected, RuleParser.parse("total >= 300 AND items >= 2"));
    }

    @Test
    void sameParsedRuleGivesDifferentAnswersForDifferentOrders() {
        RuleExpression rule = RuleParser.parse("total >= 300 AND items >= 2");

        assertTrue(rule.interpret(order("350.00", 3)));
        assertFalse(rule.interpret(order("350.00", 1)));
        assertFalse(rule.interpret(order("120.00", 3)));
    }

    @Test
    void andBindsTighterThanOr() {
        RuleExpression rule = RuleParser.parse("total >= 1000 OR items >= 2 AND items >= 5");

        // Read as "total >= 1000 OR (items >= 2 AND items >= 5)": true on the left branch alone.
        // Read left to right as "(total >= 1000 OR items >= 2) AND items >= 5" it would be false.
        assertTrue(rule.interpret(order("1000.00", 0)));
        assertEquals("(total >= 1000 OR (items >= 2 AND items >= 5))", rule.toString());
    }

    @Test
    void parenthesesOverrideTheDefaultPrecedence() {
        RuleExpression rule = RuleParser.parse("(total >= 1000 OR items >= 2) AND items >= 5");

        assertFalse(rule.interpret(order("1000.00", 0)));
        assertTrue(rule.interpret(order("1000.00", 5)));
    }

    @Test
    void notNegatesWhateverFollowsIt() {
        assertTrue(RuleParser.parse("NOT total < 100").interpret(order("100.00", 1)));
        assertFalse(RuleParser.parse("NOT (total < 100 OR items = 1)").interpret(order("500.00", 1)));
        assertTrue(RuleParser.parse("NOT NOT items = 1").interpret(order("500.00", 1)));
    }

    @Test
    void everyComparisonOperatorIsUnderstood() {
        OrderFacts facts = order("100.00", 3);

        assertTrue(RuleParser.parse("items > 2").interpret(facts));
        assertTrue(RuleParser.parse("items <= 3").interpret(facts));
        assertFalse(RuleParser.parse("items < 3").interpret(facts));
        assertTrue(RuleParser.parse("total = 100").interpret(facts));
        assertTrue(RuleParser.parse("total >= 99.5").interpret(facts));
    }

    @Test
    void malformedRulesAreRejectedWhenParsedNotWhenEvaluated() {
        assertThrows(IllegalArgumentException.class, () -> RuleParser.parse(""));
        assertThrows(IllegalArgumentException.class, () -> RuleParser.parse("total >="));
        assertThrows(IllegalArgumentException.class, () -> RuleParser.parse("total >= 300 AND"));
        assertThrows(IllegalArgumentException.class, () -> RuleParser.parse("total >= abc"));
        assertThrows(IllegalArgumentException.class, () -> RuleParser.parse("weight > 3"));
        assertThrows(IllegalArgumentException.class, () -> RuleParser.parse("total ! 300"));
        assertThrows(IllegalArgumentException.class, () -> RuleParser.parse("(total >= 300"));
        assertThrows(IllegalArgumentException.class, () -> RuleParser.parse("total >= 300 )"));
        assertThrows(IllegalArgumentException.class, () -> RuleParser.parse("total >= 300 items >= 2"));
    }
}
