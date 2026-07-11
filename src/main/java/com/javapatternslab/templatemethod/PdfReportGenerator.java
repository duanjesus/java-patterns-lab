package com.javapatternslab.templatemethod;

import java.util.List;
import java.util.stream.Collectors;

public class PdfReportGenerator extends ReportGenerator {

    @Override
    protected String formatData(List<String> data) {
        return data.stream().collect(Collectors.joining("\n"));
    }

    @Override
    protected String exportReport(String formatted) {
        return "[PDF]\n" + formatted;
    }
}
