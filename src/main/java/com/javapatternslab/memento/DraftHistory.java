package com.javapatternslab.memento;

import java.util.ArrayDeque;
import java.util.Deque;

/**
 * The caretaker: keeps snapshots and decides when to use them, without knowing what is inside.
 */
public class DraftHistory {

    private final Deque<OrderDraft.Snapshot> snapshots = new ArrayDeque<>();

    public void checkpoint(OrderDraft draft) {
        snapshots.push(draft.save());
    }

    /** Restores the most recent checkpoint. Returns false when there is nothing to undo. */
    public boolean undo(OrderDraft draft) {
        if (snapshots.isEmpty()) {
            return false;
        }
        draft.restore(snapshots.pop());
        return true;
    }

    public int size() {
        return snapshots.size();
    }
}
