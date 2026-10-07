package com.javapatternslab.interpreter;

import java.math.BigDecimal;
import java.util.ArrayList;
import java.util.List;
import java.util.regex.Matcher;
import java.util.regex.Pattern;

/**
 * Turns rule text into a tree of {@link RuleExpression}s. One method per grammar rule,
 * lowest precedence first, so AND binds tighter than OR and NOT tighter than AND:
 *
 * <pre>
 * rule       := and ( "OR" and )*
 * and        := unary ( "AND" unary )*
 * unary      := "NOT" unary | "(" rule ")" | comparison
 * comparison := variable operator number
 * </pre>
 */
public final class RuleParser {

    private static final Pattern TOKEN = Pattern.compile("\\(|\\)|>=|<=|>|<|=|[A-Za-z_]+|\\d+(?:\\.\\d+)?");

    private final List<String> tokens;
    private int position;

    private RuleParser(List<String> tokens) {
        this.tokens = tokens;
    }

    public static RuleExpression parse(String rule) {
        RuleParser parser = new RuleParser(tokenize(rule));
        RuleExpression expression = parser.parseOr();
        if (parser.position < parser.tokens.size()) {
            throw new IllegalArgumentException("Unexpected '" + parser.tokens.get(parser.position) + "' in rule: " + rule);
        }
        return expression;
    }

    private static List<String> tokenize(String rule) {
        List<String> tokens = new ArrayList<>();
        Matcher matcher = TOKEN.matcher(rule);
        int index = 0;
        while (index < rule.length()) {
            if (Character.isWhitespace(rule.charAt(index))) {
                index++;
                continue;
            }
            matcher.region(index, rule.length());
            if (!matcher.lookingAt()) {
                throw new IllegalArgumentException(
                        "Unexpected character '" + rule.charAt(index) + "' at position " + index + " in rule: " + rule);
            }
            tokens.add(matcher.group());
            index = matcher.end();
        }
        return tokens;
    }

    private RuleExpression parseOr() {
        RuleExpression left = parseAnd();
        while (accept("OR")) {
            left = new OrExpression(left, parseAnd());
        }
        return left;
    }

    private RuleExpression parseAnd() {
        RuleExpression left = parseUnary();
        while (accept("AND")) {
            left = new AndExpression(left, parseUnary());
        }
        return left;
    }

    private RuleExpression parseUnary() {
        if (accept("NOT")) {
            return new NotExpression(parseUnary());
        }
        if (accept("(")) {
            RuleExpression inner = parseOr();
            if (!accept(")")) {
                throw new IllegalArgumentException("Missing closing ')'");
            }
            return inner;
        }
        return parseComparison();
    }

    private RuleExpression parseComparison() {
        String variable = next("a variable");
        if (!OrderFacts.isVariable(variable)) {
            throw new IllegalArgumentException("Unknown variable '" + variable + "'");
        }
        ComparisonOperator operator = ComparisonOperator.fromSymbol(next("a comparison operator"));
        String number = next("a number");
        try {
            return new Comparison(variable, operator, new BigDecimal(number));
        } catch (NumberFormatException e) {
            throw new IllegalArgumentException("Expected a number but found '" + number + "'");
        }
    }

    private boolean accept(String expected) {
        if (position < tokens.size() && tokens.get(position).equals(expected)) {
            position++;
            return true;
        }
        return false;
    }

    private String next(String description) {
        if (position >= tokens.size()) {
            throw new IllegalArgumentException("Rule ended where " + description + " was expected");
        }
        return tokens.get(position++);
    }
}
