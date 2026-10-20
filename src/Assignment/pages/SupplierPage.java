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
    public final class SupplierPage extends JPanel {
        private final DefaultTableModel model = PageSupport.model("ID", "Name", "Contact person", "Phone", "Email", "Address");
        private final JTable table = new JTable(model);

        SupplierPage() {
            setLayout(new BorderLayout(8, 8));
            JPanel actions = new JPanel();
            JButton add = new JButton("Add");
            JButton edit = new JButton("Edit selected");
            JButton delete = new JButton("Delete selected");
            for (JButton button : new JButton[]{add, edit, delete}) {
                Ui.styleButton(button);
                actions.add(button);
            }
            add(actions, BorderLayout.NORTH);
            add(new JScrollPane(table));
            add.addActionListener(event -> editSupplier(-1));
            edit.addActionListener(event -> editSupplier(selectedId()));
            delete.addActionListener(event -> deleteSupplier());
            load();
        }

        private int selectedId() {
            int row = table.getSelectedRow();
            return row < 0 ? -1 : (int) model.getValueAt(row, 0);
        }

        private void load() {
            model.setRowCount(0);
            try (Connection connection = Database.connect();
                 Statement statement = connection.createStatement();
                 ResultSet result = statement.executeQuery("SELECT supplier_id,name,contact_person,phone,email,address FROM suppliers")) {
                while (result.next()) model.addRow(new Object[]{result.getInt(1), result.getString(2),
                       result.getString(3), result.getString(4), result.getString(5), result.getString(6)});
            } catch (SQLException exception) {
                Ui.showError(this, "Could not load suppliers: " + exception.getMessage());
            }
        }

        private void editSupplier(int id) {
            JTextField name = Ui.field(), contact = Ui.field(), phone = Ui.field(), email = Ui.field(), address = Ui.field();
            if (id >= 0) {
                int row = table.getSelectedRow();
                name.setText(String.valueOf(model.getValueAt(row, 1)));
                contact.setText(String.valueOf(model.getValueAt(row, 2)));
                phone.setText(String.valueOf(model.getValueAt(row, 3)));
                email.setText(String.valueOf(model.getValueAt(row, 4)));
                address.setText(String.valueOf(model.getValueAt(row, 5)));
            }
            JPanel form = PageSupport.form(new String[]{"Name", "Contact person", "Phone", "Email", "Address"},
                    new JComponent[]{name, contact, phone, email, address});
            if (JOptionPane.showConfirmDialog(this, form, "Supplier", JOptionPane.OK_CANCEL_OPTION)
                    != JOptionPane.OK_OPTION) return;
            String sql = id < 0 ? "INSERT INTO suppliers(name,contact_person,phone,email,address) VALUES(?,?,?,?,?)"
                    : "UPDATE suppliers SET name=?,contact_person=?,phone=?,email=?,address=? WHERE supplier_id=?";
            try (Connection connection = Database.connect(); PreparedStatement statement = connection.prepareStatement(sql)) {
                statement.setString(1, name.getText().trim());
                statement.setString(2, contact.getText().trim());
                statement.setString(3, phone.getText().trim());
                statement.setString(4, email.getText().trim());
                statement.setString(5, address.getText().trim());
                if (id >= 0) statement.setInt(6, id);
                statement.executeUpdate();
                load();
            } catch (SQLException exception) {
                Ui.showError(this, "Supplier could not be saved: " + exception.getMessage());
            }
        }

        private void deleteSupplier() {
            int id = selectedId();
            if (id < 0) return;
            try (Connection connection = Database.connect(); PreparedStatement statement =
                         connection.prepareStatement("DELETE FROM suppliers WHERE supplier_id=?")) {
                statement.setInt(1, id);
                statement.executeUpdate();
                load();
            } catch (SQLException exception) {
                Ui.showError(this, "Supplier could not be deleted: " + exception.getMessage());
            }
        }
    }
