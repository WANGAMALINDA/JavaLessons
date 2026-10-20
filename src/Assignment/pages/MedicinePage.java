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
    public final class MedicinePage extends JPanel {
        private final DefaultTableModel model = PageSupport.model("ID", "Name", "Company", "Type", "Supplier", "Price",
                "Quantity", "Expiry");
        private final JTable table = new JTable(model);

        MedicinePage() {
            setLayout(new BorderLayout(8, 8));
            JPanel actions = new JPanel();
            JButton add = new JButton("Add");
            JButton edit = new JButton("Edit selected");
            JButton delete = new JButton("Delete selected");
            JButton refresh = new JButton("Refresh");
            for (JButton button : new JButton[]{add, edit, delete, refresh}) {
                Ui.styleButton(button);
                actions.add(button);
            }
            add(actions, BorderLayout.NORTH);
            add(new JScrollPane(table), BorderLayout.CENTER);
            add.addActionListener(event -> editMedicine(-1));
            edit.addActionListener(event -> editMedicine(selectedId()));
            delete.addActionListener(event -> deleteMedicine());
            refresh.addActionListener(event -> load());
            load();
        }

        private int selectedId() {
            int row = table.getSelectedRow();
            return row < 0 ? -1 : (int) model.getValueAt(row, 0);
        }

        private void load() {
            model.setRowCount(0);
            String sql = "SELECT m.medicine_id,m.name,m.company,m.medicine_type,COALESCE(s.name,''),m.price,"
                    + "m.quantity_in_stock,m.expiry_date FROM medicines m "
                    + "LEFT JOIN suppliers s ON s.supplier_id=m.supplier_id ORDER BY m.name";
            try (Connection connection = Database.connect();
                 Statement statement = connection.createStatement();
                 ResultSet result = statement.executeQuery(sql)) {
                while (result.next()) {
                    model.addRow(new Object[]{result.getInt(1), result.getString(2), result.getString(3),
                            result.getString(4), result.getString(5), result.getBigDecimal(6),
                            result.getInt(7), result.getDate(8)});
                }
            } catch (SQLException exception) {
                Ui.showError(this, "Could not load medicines: " + exception.getMessage());
            }
        }

        private void editMedicine(int id) {
            JTextField name = Ui.field();
            JTextField company = Ui.field();
            JTextField type = Ui.field();
            JTextField supplier = Ui.field();
            JTextField price = Ui.field();
            JTextField quantity = Ui.field();
            JTextField expiry = Ui.field();
            JPanel form = PageSupport.form(new String[]{"Name", "Company", "Medicine type", "Supplier ID (optional)",
                    "Price", "Quantity", "Expiry (YYYY-MM-DD)"},
                    new JComponent[]{name, company, type, supplier, price, quantity, expiry});
            if (id >= 0) {
                int row = table.getSelectedRow();
                name.setText(String.valueOf(model.getValueAt(row, 1)));
                company.setText(String.valueOf(model.getValueAt(row, 2)));
                type.setText(String.valueOf(model.getValueAt(row, 3)));
                price.setText(String.valueOf(model.getValueAt(row, 5)));
                quantity.setText(String.valueOf(model.getValueAt(row, 6)));
                expiry.setText(String.valueOf(model.getValueAt(row, 7)));
            }
            if (JOptionPane.showConfirmDialog(this, form, id < 0 ? "Add medicine" : "Edit medicine",
                    JOptionPane.OK_CANCEL_OPTION) != JOptionPane.OK_OPTION) return;
            try (Connection connection = Database.connect();
                 PreparedStatement statement = connection.prepareStatement(id < 0
                         ? "INSERT INTO medicines(name,company,medicine_type,supplier_id,price,quantity_in_stock,"
                         + "expiry_date) VALUES(?,?,?, ?,?,?,?)"
                         : "UPDATE medicines SET name=?,company=?,medicine_type=?,supplier_id=?,price=?,"
                         + "quantity_in_stock=?,expiry_date=? WHERE medicine_id=?")) {
                statement.setString(1, name.getText().trim());
                statement.setString(2, company.getText().trim());
                statement.setString(3, type.getText().trim());
                PageSupport.setNullableInt(statement, 4, supplier.getText());
                statement.setBigDecimal(5, new BigDecimal(price.getText().trim()));
                statement.setInt(6, Integer.parseInt(quantity.getText().trim()));
                PageSupport.setNullableDate(statement, 7, expiry.getText());
                if (id >= 0) statement.setInt(8, id);
                statement.executeUpdate();
                load();
            } catch (SQLException | NumberFormatException exception) {
                Ui.showError(this, "Medicine could not be saved: " + exception.getMessage());
            }
        }

        private void deleteMedicine() {
            int id = selectedId();
            if (id < 0) return;
            try (Connection connection = Database.connect();
                 PreparedStatement statement = connection.prepareStatement("DELETE FROM medicines WHERE medicine_id=?")) {
                statement.setInt(1, id);
                statement.executeUpdate();
                load();
            } catch (SQLException exception) {
                Ui.showError(this, "Medicine could not be deleted. It may already be used in a sale.");
            }
        }
    }
