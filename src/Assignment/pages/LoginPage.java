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
    public final class LoginPage extends JFrame {
        private final JTextField username = Ui.field();
        private final JPasswordField password = new JPasswordField(18);

        public LoginPage() {
            setTitle("Pharmacy Management - Login");
            setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
            setSize(420, 260);
            setLocationRelativeTo(null);
            JPanel panel = new JPanel(new GridBagLayout());
            GridBagConstraints constraints = new GridBagConstraints();
            constraints.insets = new Insets(8, 8, 8, 8);
            constraints.fill = GridBagConstraints.HORIZONTAL;
            addRow(panel, constraints, 0, "Username:", username);
            addRow(panel, constraints, 1, "Password:", password);
            JButton login = new JButton("Login");
            Ui.styleButton(login);
            constraints.gridx = 1;
            constraints.gridy = 2;
            panel.add(login, constraints);
            login.addActionListener(event -> authenticate());
            password.addActionListener(event -> authenticate());
            add(panel);
        }

        private void addRow(JPanel panel, GridBagConstraints constraints, int row, String label, JComponent input) {
            constraints.gridx = 0;
            constraints.gridy = row;
            panel.add(new JLabel(label), constraints);
            constraints.gridx = 1;
            panel.add(input, constraints);
        }

        private void authenticate() {
            try {
                Database.User user = Database.authenticate(username.getText().trim(),
                        new String(password.getPassword()));
                if (user == null) {
                    Ui.showError(this, "Invalid username or password.");
                    return;
                }
                dispose();
                new DashboardFrame(user).setVisible(true);
            } catch (SQLException exception) {
                Ui.showError(this, "Login failed: " + exception.getMessage());
            }
        }
    }
