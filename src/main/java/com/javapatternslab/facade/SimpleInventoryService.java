package com.javapatternslab.facade;

public class SimpleInventoryService implements InventoryService {

    @Override
    public boolean reserveStock(String orderId) {
        System.out.println("[Inventory] Reserved stock for " + orderId);
        return true;
    }
}
