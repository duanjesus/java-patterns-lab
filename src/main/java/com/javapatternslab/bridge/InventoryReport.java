package com.javapatternslab.bridge;

import java.util.List;

public class InventoryReport extends Report {

    private final List<StockLevel> stockLevels;
    private final int lowStockThreshold;

    public InventoryReport(ReportRenderer renderer, List<StockLevel> stockLevels, int lowStockThreshold) {
        super(renderer);
        this.stockLevels = List.copyOf(stockLevels);
        this.lowStockThreshold = lowStockThreshold;
    }

    @Override
    protected String title() {
        return "Inventory report";
    }

    @Override
    protected List<ReportRow> rows() {
        return stockLevels.stream()
                .map(level -> new ReportRow(level.sku(), describe(level.units())))
                .toList();
    }

    private String describe(int units) {
        return units < lowStockThreshold ? units + " units (LOW)" : units + " units";
    }
}
