package com.javapatternslab.chainofresponsibility;

public class Level2SupportHandler extends SupportHandler {

    @Override
    protected boolean canHandle(SupportTicket ticket) {
        return ticket.severity() == Severity.MEDIUM;
    }

    @Override
    protected String resolve(SupportTicket ticket) {
        return "Level 2 resolved \"" + ticket.subject() + "\"";
    }
}
