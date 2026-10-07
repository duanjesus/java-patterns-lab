package com.javapatternslab.bridge;

import org.junit.jupiter.api.Test;

import java.math.BigDecimal;
import java.util.List;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertFalse;
import static org.junit.jupiter.api.Assertions.assertTrue;

class BridgeTest {

    private static final List<PeriodRevenue> REVENUES = List.of(
            new PeriodRevenue("2026-Q1", new BigDecimal("100.00")),
            new PeriodRevenue("2026-Q2", new BigDecimal("250.50")));

    private static final List<StockLevel> STOCK = List.of(
            new StockLevel("KEYBOARD-01", 42),
            new StockLevel("MOUSE-07", 3));

    @Test
    void sameSalesReportRendersInPlainText() {
        String output = new SalesReport(new PlainTextRenderer(), REVENUES).generate();

        assertEquals("== Sales report ==\n2026-Q1: R$100.00\n2026-Q2: R$250.50\nTotal: R$350.50", output);
    }

    @Test
    void sameSalesReportRendersInHtml() {
        String output = new SalesReport(new HtmlRenderer(), REVENUES).generate();

        assertTrue(output.startsWith("<h1>Sales report</h1><table>"));
        assertTrue(output.contains("<tr><td>2026-Q1</td><td>R$100.00</td></tr>"));
        assertTrue(output.contains("<tr><td>Total</td><td>R$350.50</td></tr>"));
    }

    @Test
    void inventoryReportFlagsLowStockWhateverTheRenderer() {
        String plain = new InventoryReport(new PlainTextRenderer(), STOCK, 5).generate();
        String html = new InventoryReport(new HtmlRenderer(), STOCK, 5).generate();

        assertTrue(plain.contains("MOUSE-07: 3 units (LOW)"));
        assertTrue(plain.contains("KEYBOARD-01: 42 units"));
        assertFalse(plain.contains("42 units (LOW)"));
        assertTrue(html.contains("<td>MOUSE-07</td><td>3 units (LOW)</td>"));
    }

    @Test
    void aNewRendererWorksWithEveryExistingReportWithoutANewReportClass() {
        ReportRenderer summaryLine = (title, rows) -> title + " (" + rows.size() + " rows)";

        assertEquals("Sales report (3 rows)", new SalesReport(summaryLine, REVENUES).generate());
        assertEquals("Inventory report (2 rows)", new InventoryReport(summaryLine, STOCK, 5).generate());
    }

    @Test
    void htmlRendererEscapesMarkupInTheData() {
        List<StockLevel> stock = List.of(new StockLevel("<b>CABLE</b> & HUB", 10));

        String output = new InventoryReport(new HtmlRenderer(), stock, 5).generate();

        assertTrue(output.contains("&lt;b&gt;CABLE&lt;/b&gt; &amp; HUB"));
        assertFalse(output.contains("<b>"));
    }
}
