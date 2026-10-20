package Assignment.pages;

import Assignment.Database;
import Assignment.Ui;
import javax.swing.*;
import javax.swing.table.*;
import java.awt.*;
import java.awt.print.*;
import java.io.*;
import java.math.*;
import java.sql.*;
import java.time.*;
    public final class ReportPage extends JPanel {
        private final JTextArea report = new JTextArea();

        ReportPage() {
            setLayout(new BorderLayout(8, 8));
            report.setEditable(false);
            JButton refresh = new JButton("Generate reports");
            JButton print = new JButton("Print report");
            Ui.styleButton(refresh);
            Ui.styleButton(print);
            JPanel actions = new JPanel();
            actions.add(refresh);
            actions.add(print);
            add(actions, BorderLayout.NORTH);
            add(new JScrollPane(report));
            refresh.addActionListener(event -> generate());
            print.addActionListener(event -> {
                try {
                    report.print();
                } catch (PrinterException exception) {
                    Ui.showError(this, "Could not print report: " + exception.getMessage());
                }
            });
            generate();
        }

        private void generate() {
            StringBuilder result = new StringBuilder("PHARMACY BUSINESS REPORTS\n\n");
            try (Connection connection = Database.connect(); Statement statement = connection.createStatement()) {
                appendSingle(result, statement, "Total medicines in catalogue",
                        "SELECT COUNT(*) FROM medicines");
                appendSingle(result, statement, "Total units in stock",
                        "SELECT COALESCE(SUM(quantity_in_stock),0) FROM medicines");
                appendSingle(result, statement, "Total sales value",
                        "SELECT COALESCE(SUM(total_amount),0) FROM sales");
                result.append("\nSALES REPORT\n");
                try (ResultSet rows = statement.executeQuery("SELECT sale_id,sale_date,total_amount "
                        + "FROM sales ORDER BY sale_date DESC LIMIT 20")) {
                    while (rows.next()) {
                        result.append("Sale #").append(rows.getInt(1)).append(" | ")
                                .append(rows.getTimestamp(2)).append(" | ")
                                .append(rows.getBigDecimal(3)).append('\n');
                    }
                }
                result.append("\nLOW STOCK (at or below reorder level)\n");
                try (ResultSet rows = statement.executeQuery("SELECT name,quantity_in_stock FROM medicines "
                        + "WHERE quantity_in_stock<=reorder_level ORDER BY quantity_in_stock")) {
                    while (rows.next()) result.append(rows.getString(1)).append(" - ")
                            .append(rows.getInt(2)).append(" units\n");
                }
                result.append("\nEXPIRING IN THE NEXT MONTH\n");
                try (ResultSet rows = statement.executeQuery("SELECT name,expiry_date,quantity_in_stock "
                        + "FROM medicines WHERE expiry_date BETWEEN CURDATE() "
                        + "AND DATE_ADD(CURDATE(), INTERVAL 1 MONTH) ORDER BY expiry_date")) {
                    while (rows.next()) {
                        result.append(rows.getString(1)).append(" - expires ")
                                .append(rows.getDate(2)).append(" - ")
                                .append(rows.getInt(3)).append(" units in stock\n");
                    }
                }
                result.append("\nTOP SELLING MEDICINES\n");
                try (ResultSet rows = statement.executeQuery("SELECT m.name,SUM(si.quantity_sold) sold "
                        + "FROM sale_items si JOIN medicines m ON m.medicine_id=si.medicine_id "
                        + "GROUP BY m.medicine_id,m.name ORDER BY sold DESC LIMIT 10")) {
                    while (rows.next()) result.append(rows.getString(1)).append(" - ")
                            .append(rows.getInt(2)).append(" units sold\n");
                }
                report.setText(result.toString());
            } catch (SQLException exception) {
                Ui.showError(this, "Could not generate reports: " + exception.getMessage());
            }
        }

        private void appendSingle(StringBuilder result, Statement statement, String label, String sql)
                throws SQLException {
            try (ResultSet rows = statement.executeQuery(sql)) {
                rows.next();
                result.append(label).append(": ").append(rows.getString(1)).append('\n');
            }
        }
    }
