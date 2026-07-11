package com.javapatternslab.adapter;

/**
 * Stand-in for a third-party client with an old, incompatible API:
 * amounts as whole cents in a string, and a raw status string reply.
 * This class is treated as unmodifiable, as if it shipped in a jar
 * we don't own.
 */
public class LegacyPaymentGateway {

    public String submitTransaction(String amountInCents, String currencyCode) {
        System.out.println("[LegacyPaymentGateway] submitTransaction(" + amountInCents + ", " + currencyCode + ")");
        return "APPROVED";
    }
}
