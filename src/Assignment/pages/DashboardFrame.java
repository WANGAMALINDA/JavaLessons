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
    public final class DashboardFrame extends JFrame {
        public DashboardFrame(Database.User user) {
            setTitle("Pharmacy Management - " + user.fullName + " (" + user.role + ")");
            setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
            setSize(1050, 700);
            setLocationRelativeTo(null);
            if ("Admin".equals(user.role)) {
                add(new AdminDashboardPage());
            } else {
                add(new CashierDashboardPage(user));
            }
        }
    }
