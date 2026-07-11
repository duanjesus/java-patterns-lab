package com.javapatternslab.templatemethod;

public class TemplateMethodDemo {

    public static void main(String[] args) {
        System.out.println("-- Template Method: same fetch step, diverging format/export steps --");

        ReportGenerator pdfGenerator = new PdfReportGenerator();
        System.out.println(pdfGenerator.generate());

        System.out.println();

        ReportGenerator csvGenerator = new CsvReportGenerator();
        System.out.println(csvGenerator.generate());
    }
}
