package Assignment.pages;

import javax.swing.*;
import javax.swing.table.DefaultTableModel;
import java.awt.*;
import java.sql.PreparedStatement;
import java.sql.SQLException;
import java.sql.Types;
import java.time.LocalDate;

final class PageSupport {
    private PageSupport() {
    }

    static DefaultTableModel model(String... columns) {
        return new DefaultTableModel(columns, 0) {
            @Override
            public boolean isCellEditable(int row, int column) {
                return false;
            }
        };
    }

    static JPanel form(String[] labels, JComponent[] inputs) {
        JPanel panel = new JPanel(new GridLayout(labels.length, 2, 6, 6));
        for (int index = 0; index < labels.length; index++) {
            panel.add(new JLabel(labels[index] + ":"));
            panel.add(inputs[index]);
        }
        return panel;
    }

    static void setNullableInt(PreparedStatement statement, int index, String value) throws SQLException {
        if (value.trim().isEmpty()) {
            statement.setNull(index, Types.INTEGER);
        } else {
            statement.setInt(index, Integer.parseInt(value.trim()));
        }
    }

    static void setNullableDate(PreparedStatement statement, int index, String value) throws SQLException {
        if (value.trim().isEmpty()) {
            statement.setNull(index, Types.DATE);
        } else {
            statement.setDate(index, java.sql.Date.valueOf(LocalDate.parse(value.trim())));
        }
    }
}
