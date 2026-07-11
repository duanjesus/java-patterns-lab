package com.javapatternslab.chainofresponsibility;

public class ManagerHandler extends SupportHandler {

    @Override
    protected boolean canHandle(SupportTicket ticket) {
        return ticket.severity() == Severity.CRITICAL;
    }

    @Override
    protected String resolve(SupportTicket ticket) {
        return "Manager resolved \"" + ticket.subject() + "\"";
    }
}
