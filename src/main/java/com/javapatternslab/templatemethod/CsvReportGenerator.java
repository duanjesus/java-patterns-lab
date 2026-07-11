package com.javapatternslab.templatemethod;

import java.util.List;
import java.util.stream.Collectors;

public class CsvReportGenerator extends ReportGenerator {

    @Override
    protected String formatData(List<String> data) {
        return data.stream().collect(Collectors.joining(","));
    }

    @Override
    protected String exportReport(String formatted) {
        return "period,revenue\n" + formatted;
    }
}
