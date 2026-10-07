package com.javapatternslab.bridge;

import java.util.List;

public class PlainTextRenderer implements ReportRenderer {

    @Override
    public String render(String title, List<ReportRow> rows) {
        StringBuilder text = new StringBuilder("== " + title + " ==");
        for (ReportRow row : rows) {
            text.append("\n").append(row.label()).append(": ").append(row.value());
        }
        return text.toString();
    }
}
