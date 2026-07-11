package com.javapatternslab.builder;

import java.math.BigDecimal;

public record LineItem(String description, BigDecimal price) {
}
