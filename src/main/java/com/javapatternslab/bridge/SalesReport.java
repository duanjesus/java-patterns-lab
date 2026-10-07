package com.javapatternslab.bridge;

import java.math.BigDecimal;
import java.util.ArrayList;
import java.util.List;

public class SalesReport extends Report {

    private final List<PeriodRevenue> revenues;

    public SalesReport(ReportRenderer renderer, List<PeriodRevenue> revenues) {
        super(renderer);
        this.revenues = List.copyOf(revenues);
    }

    @Override
    protected String title() {
        return "Sales report";
    }

    @Override
    protected List<ReportRow> rows() {
        List<ReportRow> rows = new ArrayList<>();
        BigDecimal total = BigDecimal.ZERO;
        for (PeriodRevenue revenue : revenues) {
            rows.add(new ReportRow(revenue.period(), "R$" + revenue.revenue()));
            total = total.add(revenue.revenue());
        }
        rows.add(new ReportRow("Total", "R$" + total));
        return rows;
    }
}
