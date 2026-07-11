package com.javapatternslab.facade;

import java.math.BigDecimal;

public interface PaymentService {

    boolean charge(String orderId, BigDecimal amount);
}
