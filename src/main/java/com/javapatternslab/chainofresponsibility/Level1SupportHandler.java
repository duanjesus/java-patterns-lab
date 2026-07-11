package com.javapatternslab.chainofresponsibility;

public class Level1SupportHandler extends SupportHandler {

    @Override
    protected boolean canHandle(SupportTicket ticket) {
        return ticket.severity() == Severity.LOW;
    }

    @Override
    protected String resolve(SupportTicket ticket) {
        return "Level 1 resolved \"" + ticket.subject() + "\"";
    }
}
