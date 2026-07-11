package com.javapatternslab.strategy;

import java.math.BigDecimal;

public interface PaymentStrategy {

    String pay(BigDecimal amount);
}
