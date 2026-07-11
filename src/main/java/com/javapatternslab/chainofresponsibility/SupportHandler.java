package com.javapatternslab.chainofresponsibility;

public abstract class SupportHandler {

    private SupportHandler next;

    public SupportHandler setNext(SupportHandler next) {
        this.next = next;
        return next;
    }

    public final String handle(SupportTicket ticket) {
        if (canHandle(ticket)) {
            return resolve(ticket);
        }
        if (next != null) {
            return next.handle(ticket);
        }
        return "Ticket \"" + ticket.subject() + "\" (" + ticket.severity() + ") reached the end of the chain unresolved";
    }

    protected abstract boolean canHandle(SupportTicket ticket);

    protected abstract String resolve(SupportTicket ticket);
}
