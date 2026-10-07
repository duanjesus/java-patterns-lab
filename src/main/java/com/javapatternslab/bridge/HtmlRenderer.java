package com.javapatternslab.bridge;

import java.util.List;

public class HtmlRenderer implements ReportRenderer {

    @Override
    public String render(String title, List<ReportRow> rows) {
        StringBuilder html = new StringBuilder("<h1>" + escape(title) + "</h1><table>");
        for (ReportRow row : rows) {
            html.append("<tr><td>").append(escape(row.label()))
                    .append("</td><td>").append(escape(row.value())).append("</td></tr>");
        }
        return html.append("</table>").toString();
    }

    private static String escape(String text) {
        return text.replace("&", "&amp;").replace("<", "&lt;").replace(">", "&gt;");
    }
}
