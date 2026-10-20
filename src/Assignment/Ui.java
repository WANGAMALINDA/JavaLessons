package Assignment;

import javax.swing.*;
import java.awt.*;

public final class Ui {
    public static final Color PRIMARY = new Color(25, 92, 135);

    private Ui() {
    }

    public static void showError(Component parent, String message) {
        JOptionPane.showMessageDialog(parent, message, "Pharmacy application", JOptionPane.ERROR_MESSAGE);
    }

    public static void showInfo(Component parent, String message) {
        JOptionPane.showMessageDialog(parent, message, "Pharmacy application", JOptionPane.INFORMATION_MESSAGE);
    }

    public static JTextField field() {
        return new JTextField(18);
    }

    public static void styleButton(JButton button) {
        button.setBackground(PRIMARY);
        button.setForeground(Color.WHITE);
        button.setFocusPainted(false);
    }
}
