package com.javapatternslab.command;

import org.junit.jupiter.api.Test;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertFalse;
import static org.junit.jupiter.api.Assertions.assertTrue;

class CommandTest {

    @Test
    void placeOrderCommandPlacesThenUndoesToCancelled() {
        OrderBook orderBook = new OrderBook();
        Command command = new PlaceOrderCommand(orderBook, "ORD-1");

        command.execute();
        assertTrue(orderBook.isPlaced("ORD-1"));

        command.undo();
        assertFalse(orderBook.isPlaced("ORD-1"));
    }

    @Test
    void invokerTracksHistoryAndUndoesLastCommandFirst() {
        OrderBook orderBook = new OrderBook();
        OrderInvoker invoker = new OrderInvoker();

        invoker.run(new PlaceOrderCommand(orderBook, "ORD-1"));
        invoker.run(new PlaceOrderCommand(orderBook, "ORD-2"));
        assertEquals(2, invoker.historySize());
        assertTrue(orderBook.isPlaced("ORD-1"));
        assertTrue(orderBook.isPlaced("ORD-2"));

        invoker.undoLast();

        assertFalse(orderBook.isPlaced("ORD-2"), "undo should reverse the most recently executed command first");
        assertTrue(orderBook.isPlaced("ORD-1"), "earlier commands should be untouched by undoing the last one");
        assertEquals(1, invoker.historySize());
    }

    @Test
    void undoOnEmptyHistoryIsSafe() {
        OrderInvoker invoker = new OrderInvoker();

        String result = invoker.undoLast();

        assertEquals("Nothing to undo", result);
    }
}
