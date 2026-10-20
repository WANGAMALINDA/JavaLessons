package Assignment;

import Assignment.pages.LoginPage;
import javax.swing.*;
import java.sql.SQLException;

public final class PharmacyApplication {
    private PharmacyApplication() {
    }

    public static void main(String[] args) {
        SwingUtilities.invokeLater(() -> {
            try {
                Database.initialize();
                new LoginPage().setVisible(true);
            } catch (SQLException exception) {
                Ui.showError(null, "Could not initialize MySQL database:\n" + exception.getMessage()
                        + "\nStart MySQL in XAMPP and try again.");
            }
        });
    }
}