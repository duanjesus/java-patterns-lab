package com.javapatternslab.adapter;

import java.math.BigDecimal;

public interface PaymentGateway {

    boolean charge(BigDecimal amount);
}
