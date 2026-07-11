package com.javapatternslab.templatemethod;

import org.junit.jupiter.api.Test;

import static org.junit.jupiter.api.Assertions.assertTrue;

class TemplateMethodTest {

    @Test
    void pdfGeneratorWrapsFormattedDataWithPdfHeader() {
        String report = new PdfReportGenerator().generate();

        assertTrue(report.startsWith("[PDF]"));
        assertTrue(report.contains("2026-Q1: R$12,400"));
    }

    @Test
    void csvGeneratorProducesCommaSeparatedRowsWithCsvHeader() {
        String report = new CsvReportGenerator().generate();

        assertTrue(report.startsWith("period,revenue"));
        assertTrue(report.contains("2026-Q1: R$12,400,2026-Q2: R$15,900"));
    }

    @Test
    void bothGeneratorsShareTheSameUnderlyingData() {
        String pdfReport = new PdfReportGenerator().generate();
        String csvReport = new CsvReportGenerator().generate();

        assertTrue(pdfReport.contains("2026-Q3: R$14,100"));
        assertTrue(csvReport.contains("2026-Q3: R$14,100"));
    }
}
