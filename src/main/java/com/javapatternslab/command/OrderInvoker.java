package com.javapatternslab.command;

import java.util.ArrayDeque;
import java.util.Deque;

public class OrderInvoker {

    private final Deque<Command> history = new ArrayDeque<>();

    public String run(Command command) {
        String result = command.execute();
        history.push(command);
        return result;
    }

    public String undoLast() {
        if (history.isEmpty()) {
            return "Nothing to undo";
        }
        return history.pop().undo();
    }

    public int historySize() {
        return history.size();
    }
}
