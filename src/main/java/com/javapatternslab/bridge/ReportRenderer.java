package com.javapatternslab.bridge;

import java.util.List;

public interface ReportRenderer {

    String render(String title, List<ReportRow> rows);
}
