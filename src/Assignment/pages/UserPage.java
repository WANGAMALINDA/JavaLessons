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
    public final class UserPage extends JPanel {
        private final DefaultTableModel model = PageSupport.model("ID", "Username", "Role", "Full name", "Active");
        private final JTable table = new JTable(model);

        UserPage() {
            setLayout(new BorderLayout(8, 8));
            JButton add = new JButton("Create cashier");
            JButton delete = new JButton("Deactivate selected");
            Ui.styleButton(add);
            Ui.styleButton(delete);
            JPanel actions = new JPanel();
            actions.add(add);
            actions.add(delete);
            add(actions, BorderLayout.NORTH);
            add(new JScrollPane(table));
            add.addActionListener(event -> createCashier());
            delete.addActionListener(event -> deactivate());
            load();
        }

        private void load() {
            model.setRowCount(0);
            try (Connection connection = Database.connect(); Statement statement = connection.createStatement();
                 ResultSet result = statement.executeQuery("SELECT user_id,username,role,full_name,active FROM users")) {
                while (result.next()) model.addRow(new Object[]{result.getInt(1), result.getString(2),
                        result.getString(3), result.getString(4), result.getBoolean(5)});
            } catch (SQLException exception) {
                Ui.showError(this, "Could not load users: " + exception.getMessage());
            }
        }

        private void createCashier() {
            JTextField username = Ui.field(), fullName = Ui.field();
            JPasswordField password = new JPasswordField(18);
            JPanel form = PageSupport.form(new String[]{"Username", "Full name", "Password"},
                    new JComponent[]{username, fullName, password});
            if (JOptionPane.showConfirmDialog(this, form, "Create cashier", JOptionPane.OK_CANCEL_OPTION)
                    != JOptionPane.OK_OPTION) return;
            try (Connection connection = Database.connect(); PreparedStatement statement = connection.prepareStatement(
                    "INSERT INTO users(username,password,role,full_name) VALUES(?,?, 'Cashier',?)")) {
                statement.setString(1, username.getText().trim());
                statement.setString(2, Database.hashPassword(new String(password.getPassword())));
                statement.setString(3, fullName.getText().trim());
                statement.executeUpdate();
                load();
            } catch (SQLException exception) {
                Ui.showError(this, "Cashier could not be created: " + exception.getMessage());
            }
        }

        private void deactivate() {
            int row = table.getSelectedRow();
            if (row < 0) return;
            try (Connection connection = Database.connect(); PreparedStatement statement =
                         connection.prepareStatement("UPDATE users SET active=FALSE WHERE user_id=? AND role='Cashier'")) {
                statement.setInt(1, (int) model.getValueAt(row, 0));
                statement.executeUpdate();
                load();
            } catch (SQLException exception) {
                Ui.showError(this, "User could not be deactivated: " + exception.getMessage());
            }
        }
    }
