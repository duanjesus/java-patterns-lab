package com.javapatternslab.command;

public class PlaceOrderCommand implements Command {

    private final OrderBook orderBook;
    private final String orderId;

    public PlaceOrderCommand(OrderBook orderBook, String orderId) {
        this.orderBook = orderBook;
        this.orderId = orderId;
    }

    @Override
    public String execute() {
        orderBook.place(orderId);
        return "Placed order " + orderId;
    }

    @Override
    public String undo() {
        orderBook.cancel(orderId);
        return "Undid placement of order " + orderId;
    }
}
