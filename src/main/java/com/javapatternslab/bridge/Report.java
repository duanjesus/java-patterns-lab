package com.javapatternslab.bridge;

import java.util.List;

public abstract class Report {

    private final ReportRenderer renderer;

    protected Report(ReportRenderer renderer) {
        this.renderer = renderer;
    }

    public String generate() {
        return renderer.render(title(), rows());
    }

    protected abstract String title();

    protected abstract List<ReportRow> rows();
}
