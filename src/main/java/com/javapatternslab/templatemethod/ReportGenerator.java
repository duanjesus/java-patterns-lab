package com.javapatternslab.templatemethod;

import java.util.List;

public abstract class ReportGenerator {

    public final String generate() {
        List<String> data = fetchData();
        String formatted = formatData(data);
        return exportReport(formatted);
    }

    protected List<String> fetchData() {
        return List.of("2026-Q1: R$12,400", "2026-Q2: R$15,900", "2026-Q3: R$14,100");
    }

    protected abstract String formatData(List<String> data);

    protected abstract String exportReport(String formatted);
}
