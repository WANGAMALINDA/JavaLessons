package Assignment.pages;

import Assignment.Database;
import Assignment.Ui;
import javax.swing.*;
import javax.swing.table.*;
import java.awt.*;
import java.awt.print.*;
import java.io.*;
import java.math.*;
import java.nio.charset.StandardCharsets;
import java.nio.file.*;
import java.sql.*;
import java.time.*;
    public final class PosPage extends JPanel {
        private final Database.User cashier;
        private final DefaultTableModel model = PageSupport.model("Medicine ID", "Medicine", "Unit price", "Quantity", "Line total");
        private final JTable table = new JTable(model);
        private final JTextField medicineId = Ui.field();
        private final JTextField quantity = Ui.field();
        private final JLabel total = new JLabel("Total: 0.00");
        private BigDecimal totalValue = BigDecimal.ZERO;

        PosPage(Database.User cashier) {
            this.cashier = cashier;
            setLayout(new BorderLayout(8, 8));
            JPanel top = new JPanel();
            top.add(new JLabel("Medicine ID:"));
            top.add(medicineId);
            top.add(new JLabel("Quantity:"));
            top.add(quantity);
            JButton add = new JButton("Add item");
            JButton complete = new JButton("Complete sale");
            JButton clear = new JButton("Clear");
            Ui.styleButton(add);
            Ui.styleButton(complete);
            Ui.styleButton(clear);
            top.add(add);
            top.add(complete);
            top.add(clear);
            add(top, BorderLayout.NORTH);
            add(new JScrollPane(table), BorderLayout.CENTER);
            JPanel bottom = new JPanel(new BorderLayout());
            bottom.add(total, BorderLayout.WEST);
            add(bottom, BorderLayout.SOUTH);
            add.addActionListener(event -> addItem());
            complete.addActionListener(event -> completeSale());
            clear.addActionListener(event -> clearSale());
        }

        private void addItem() {
            try (Connection connection = Database.connect(); PreparedStatement statement = connection.prepareStatement(
                    "SELECT name,price,quantity_in_stock FROM medicines WHERE medicine_id=?")) {
                int id = Integer.parseInt(medicineId.getText().trim());
                int amount = Integer.parseInt(quantity.getText().trim());
                statement.setInt(1, id);
                try (ResultSet result = statement.executeQuery()) {
                    if (!result.next() || amount <= 0 || result.getInt(3) < amount) {
                        Ui.showError(this, "Medicine not found or insufficient stock.");
                        return;
                    }
                    BigDecimal price = result.getBigDecimal(2);
                    BigDecimal line = price.multiply(BigDecimal.valueOf(amount));
                    model.addRow(new Object[]{id, result.getString(1), price, amount, line});
                    totalValue = totalValue.add(line);
                    total.setText("Total: " + totalValue);
                }
            } catch (SQLException | NumberFormatException exception) {
                Ui.showError(this, "Could not add item: " + exception.getMessage());
            }
        }

        private void completeSale() {
            if (model.getRowCount() == 0) return;
            try (Connection connection = Database.connect()) {
                connection.setAutoCommit(false);
                try (PreparedStatement sale = connection.prepareStatement(
                        "INSERT INTO sales(user_id,total_amount) VALUES(?,?)", Statement.RETURN_GENERATED_KEYS)) {
                    sale.setInt(1, cashier.id);
                    sale.setBigDecimal(2, totalValue);
                    sale.executeUpdate();
                    try (ResultSet keys = sale.getGeneratedKeys()) {
                        keys.next();
                        int saleId = keys.getInt(1);
                        for (int row = 0; row < model.getRowCount(); row++) {
                            int medicineId = (int) model.getValueAt(row, 0);
                            int amount = (int) model.getValueAt(row, 3);
                            BigDecimal price = (BigDecimal) model.getValueAt(row, 2);
                            try (PreparedStatement item = connection.prepareStatement(
                                    "INSERT INTO sale_items(sale_id,medicine_id,quantity_sold,price_at_sale) VALUES(?,?,?,?)");
                                 PreparedStatement stock = connection.prepareStatement(
                                         "UPDATE medicines SET quantity_in_stock=quantity_in_stock-? "
                                         + "WHERE medicine_id=? AND quantity_in_stock>=?")) {
                                item.setInt(1, saleId); item.setInt(2, medicineId); item.setInt(3, amount);
                                item.setBigDecimal(4, price); item.executeUpdate();
                                stock.setInt(1, amount); stock.setInt(2, medicineId); stock.setInt(3, amount);
                                if (stock.executeUpdate() != 1) throw new SQLException("Stock changed during checkout.");
                            }
                        }
                        connection.commit();
                        String bill = createBill(saleId);
                        Ui.showInfo(this, "Sale completed. Bill saved to:\n" + bill);
                        clearSale();
                    }
                } catch (SQLException exception) {
                    connection.rollback();
                    throw exception;
                }
            } catch (SQLException exception) {
                Ui.showError(this, "Sale could not be completed: " + exception.getMessage());
            } catch (IOException exception) {
                Ui.showError(this, "Sale completed, but the bill could not be saved: "
                        + exception.getMessage());
            }
        }

        private String createBill(int saleId) throws IOException {
            Path home = Paths.get(System.getProperty("user.home"));
            Path preferredDirectory = home.resolve("Documents").resolve("PharmacyBills");
            Path fallbackDirectory = home.resolve("PharmacyBills");
            IOException accessDenied = null;

            for (Path billDirectory : new Path[]{preferredDirectory, fallbackDirectory}) {
                try {
                    Files.createDirectories(billDirectory);
                    Path file = billDirectory.resolve("bill-" + saleId + ".txt");
                    try (BufferedWriter writer = Files.newBufferedWriter(file, StandardCharsets.UTF_8)) {
                        writer.write("PHARMACY BILL\nSale number: " + saleId + "\nCashier: "
                                + cashier.fullName + "\n\n");
                        for (int row = 0; row < model.getRowCount(); row++) {
                            writer.write(model.getValueAt(row, 1) + " x " + model.getValueAt(row, 3)
                                    + " = " + model.getValueAt(row, 4) + "\n");
                        }
                        writer.write("\nTOTAL: " + totalValue + "\n");
                    }
                    return file.toAbsolutePath().toString();
                } catch (AccessDeniedException exception) {
                    accessDenied = exception;
                }
            }

            throw new IOException("Access denied when saving the bill to "
                    + preferredDirectory + " or " + fallbackDirectory, accessDenied);
        }

        private void clearSale() {
            model.setRowCount(0);
            totalValue = BigDecimal.ZERO;
            total.setText("Total: 0.00");
        }
    }
