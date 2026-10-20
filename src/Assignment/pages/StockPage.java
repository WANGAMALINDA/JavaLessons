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
    public final class StockPage extends JPanel {
        private final DefaultTableModel model = PageSupport.model("Medicine", "Category", "Price", "Available", "Expiry");
        private final JTable table = new JTable(model);

        StockPage() {
            setLayout(new BorderLayout(8, 8));
            JButton refresh = new JButton("Refresh stock");
            Ui.styleButton(refresh);
            add(refresh, BorderLayout.NORTH);
            add(new JScrollPane(table));
            refresh.addActionListener(event -> load());
            load();
        }

        private void load() {
            model.setRowCount(0);
            try (Connection connection = Database.connect(); Statement statement = connection.createStatement();
                 ResultSet result = statement.executeQuery("SELECT name,medicine_type,price,quantity_in_stock,expiry_date "
                         + "FROM medicines ORDER BY name")) {
                while (result.next()) model.addRow(new Object[]{result.getString(1), result.getString(2),
                        result.getBigDecimal(3), result.getInt(4), result.getDate(5)});
            } catch (SQLException exception) {
                Ui.showError(this, "Could not load stock: " + exception.getMessage());
            }
        }
    }
