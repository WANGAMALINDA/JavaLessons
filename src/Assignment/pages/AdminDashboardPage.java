package Assignment.pages;

import javax.swing.*;
import java.awt.*;

public final class AdminDashboardPage extends JPanel {
    public AdminDashboardPage() {
        setLayout(new BorderLayout());
        JTabbedPane pages = new JTabbedPane();
        pages.addTab("Manage medicines", new MedicinePage());
        pages.addTab("Manage suppliers", new SupplierPage());
        pages.addTab("Manage users", new UserPage());
        pages.addTab("Reports", new ReportPage());
        add(pages, BorderLayout.CENTER);
    }
}
