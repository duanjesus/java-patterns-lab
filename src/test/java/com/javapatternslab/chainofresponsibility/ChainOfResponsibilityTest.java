package com.javapatternslab.chainofresponsibility;

import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;

import static org.junit.jupiter.api.Assertions.assertTrue;

class ChainOfResponsibilityTest {

    private SupportHandler chain;

    @BeforeEach
    void setUp() {
        SupportHandler level1 = new Level1SupportHandler();
        SupportHandler level2 = new Level2SupportHandler();
        SupportHandler manager = new ManagerHandler();
        level1.setNext(level2).setNext(manager);
        chain = level1;
    }

    @Test
    void lowSeverityTicketIsResolvedByLevel1() {
        String result = chain.handle(new SupportTicket("Password reset", Severity.LOW));

        assertTrue(result.startsWith("Level 1 resolved"));
    }

    @Test
    void mediumSeverityTicketSkipsLevel1AndIsResolvedByLevel2() {
        String result = chain.handle(new SupportTicket("Billing discrepancy", Severity.MEDIUM));

        assertTrue(result.startsWith("Level 2 resolved"));
    }

    @Test
    void criticalSeverityTicketReachesManager() {
        String result = chain.handle(new SupportTicket("Data breach report", Severity.CRITICAL));

        assertTrue(result.startsWith("Manager resolved"));
    }

    @Test
    void ticketFallsThroughUnresolvedWhenChainCantHandleIt() {
        SupportHandler level1Only = new Level1SupportHandler();

        String result = level1Only.handle(new SupportTicket("Data breach report", Severity.CRITICAL));

        assertTrue(result.contains("unresolved"));
    }
}
