package com.javapatternslab.command;

public class CancelOrderCommand implements Command {

    private final OrderBook orderBook;
    private final String orderId;

    public CancelOrderCommand(OrderBook orderBook, String orderId) {
        this.orderBook = orderBook;
        this.orderId = orderId;
    }

    @Override
    public String execute() {
        orderBook.cancel(orderId);
        return "Cancelled order " + orderId;
    }

    @Override
    public String undo() {
        orderBook.place(orderId);
        return "Undid cancellation of order " + orderId;
    }
}
