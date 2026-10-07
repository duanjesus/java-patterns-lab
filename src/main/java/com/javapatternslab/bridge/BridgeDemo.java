package com.javapatternslab.bridge;

import java.math.BigDecimal;
import java.util.List;

public class BridgeDemo {

    public static void main(String[] args) {
        System.out.println("-- Bridge: 2 report types x 2 output formats, with no class per combination --");

        List<PeriodRevenue> revenues = List.of(
                new PeriodRevenue("2026-Q1", new BigDecimal("12400.00")),
                new PeriodRevenue("2026-Q2", new BigDecimal("15900.00")));
        List<StockLevel> stock = List.of(
                new StockLevel("KEYBOARD-01", 42),
                new StockLevel("MOUSE-07", 3));

        for (ReportRenderer renderer : List.of(new PlainTextRenderer(), new HtmlRenderer())) {
            System.out.println();
            System.out.println("[" + renderer.getClass().getSimpleName() + "]");
            System.out.println(new SalesReport(renderer, revenues).generate());
            System.out.println(new InventoryReport(renderer, stock, 5).generate());
        }
    }
}
