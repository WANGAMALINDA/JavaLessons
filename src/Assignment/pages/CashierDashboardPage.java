package Assignment.pages;

import Assignment.Database;
import javax.swing.*;
import java.awt.*;

public final class CashierDashboardPage extends JPanel {
    public CashierDashboardPage(Database.User cashier) {
        setLayout(new BorderLayout());
        JTabbedPane pages = new JTabbedPane();
        pages.addTab("Point of sale", new PosPage(cashier));
        pages.addTab("Stock check", new StockPage());
        add(pages, BorderLayout.CENTER);
    }
}
