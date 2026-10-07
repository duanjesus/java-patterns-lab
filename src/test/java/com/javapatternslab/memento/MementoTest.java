package com.javapatternslab.memento;

import org.junit.jupiter.api.Test;

import java.math.BigDecimal;
import java.util.List;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertFalse;
import static org.junit.jupiter.api.Assertions.assertTrue;

class MementoTest {

    private static OrderDraft draftWithKeyboard() {
        OrderDraft draft = new OrderDraft();
        draft.addItem("Keyboard", new BigDecimal("350.00"));
        draft.setShippingAddress("Rua A, 1");
        return draft;
    }

    @Test
    void restoreBringsBackItemsCouponAndAddress() {
        OrderDraft draft = draftWithKeyboard();
        OrderDraft.Snapshot snapshot = draft.save();

        draft.addItem("Mouse", new BigDecimal("120.00"));
        draft.applyCoupon("DESC10");
        draft.setShippingAddress("Av. B, 2");
        draft.restore(snapshot);

        assertEquals(List.of(new DraftItem("Keyboard", new BigDecimal("350.00"))), draft.getItems());
        assertEquals("", draft.getCouponCode());
        assertEquals("Rua A, 1", draft.getShippingAddress());
        assertEquals(0, draft.getTotal().compareTo(new BigDecimal("350.00")));
    }

    @Test
    void snapshotIsNotAffectedByChangesMadeAfterItWasTaken() {
        OrderDraft draft = draftWithKeyboard();
        OrderDraft.Snapshot snapshot = draft.save();

        draft.removeItem("Keyboard");
        assertTrue(draft.getItems().isEmpty());
        draft.restore(snapshot);

        assertEquals(1, draft.getItems().size(), "the snapshot must hold its own copy of the item list");
    }

    @Test
    void sameSnapshotCanBeRestoredMoreThanOnce() {
        OrderDraft draft = draftWithKeyboard();
        OrderDraft.Snapshot snapshot = draft.save();

        draft.restore(snapshot);
        draft.addItem("Mouse", new BigDecimal("120.00"));
        draft.restore(snapshot);

        assertEquals(1, draft.getItems().size());
    }

    @Test
    void historyUndoesCheckpointsMostRecentFirst() {
        OrderDraft draft = draftWithKeyboard();
        DraftHistory history = new DraftHistory();

        history.checkpoint(draft);
        draft.addItem("Mouse", new BigDecimal("120.00"));
        history.checkpoint(draft);
        draft.addItem("Monitor", new BigDecimal("1200.00"));
        assertEquals(3, draft.getItems().size());

        assertTrue(history.undo(draft));
        assertEquals(2, draft.getItems().size());

        assertTrue(history.undo(draft));
        assertEquals(1, draft.getItems().size());
        assertEquals(0, history.size());
    }

    @Test
    void undoWithNoCheckpointsLeavesTheDraftAsItIs() {
        OrderDraft draft = draftWithKeyboard();
        DraftHistory history = new DraftHistory();

        assertFalse(history.undo(draft));
        assertEquals(1, draft.getItems().size());
        assertEquals("Rua A, 1", draft.getShippingAddress());
    }
}
