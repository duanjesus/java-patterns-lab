package com.javapatternslab.command;

public class CommandDemo {

    public static void main(String[] args) {
        OrderBook orderBook = new OrderBook();
        OrderInvoker invoker = new OrderInvoker();

        System.out.println("-- Command: place/cancel as objects, with undo --");
        System.out.println(invoker.run(new PlaceOrderCommand(orderBook, "ORD-2001")));
        System.out.println(invoker.run(new CancelOrderCommand(orderBook, "ORD-1999")));
        System.out.println("History size: " + invoker.historySize());

        System.out.println(invoker.undoLast());
        System.out.println(invoker.undoLast());
        System.out.println("History size: " + invoker.historySize());
    }
}
