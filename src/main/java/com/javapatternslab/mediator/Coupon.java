package com.javapatternslab.mediator;

import java.util.Locale;

public enum Coupon {
    NONE,
    DESC10,
    FRETEGRATIS;

    public static Coupon fromCode(String code) {
        if (code == null) {
            return NONE;
        }
        return switch (code.trim().toUpperCase(Locale.ROOT)) {
            case "DESC10" -> DESC10;
            case "FRETEGRATIS" -> FRETEGRATIS;
            default -> NONE;
        };
    }
}
