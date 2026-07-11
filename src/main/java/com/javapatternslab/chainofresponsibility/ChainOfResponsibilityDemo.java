package com.javapatternslab.chainofresponsibility;

public class ChainOfResponsibilityDemo {

    public static void main(String[] args) {
        SupportHandler level1 = new Level1SupportHandler();
        SupportHandler level2 = new Level2SupportHandler();
        SupportHandler manager = new ManagerHandler();
        level1.setNext(level2).setNext(manager);

        System.out.println("-- Chain of Responsibility: L1 -> L2 -> Manager --");
        System.out.println(level1.handle(new SupportTicket("Password reset", Severity.LOW)));
        System.out.println(level1.handle(new SupportTicket("Billing discrepancy", Severity.MEDIUM)));
        System.out.println(level1.handle(new SupportTicket("Data breach report", Severity.CRITICAL)));
    }
}
