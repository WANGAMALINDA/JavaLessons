package Assignment;

import java.nio.charset.StandardCharsets;
import java.security.MessageDigest;
import java.security.NoSuchAlgorithmException;
import java.sql.*;

public final class Database {
    private static final String SERVER_URL = "jdbc:mysql://localhost:3306/";
    private static final String DATABASE_URL = "jdbc:mysql://localhost:3306/pharmacydb";
    private static final String USER = "root";
    private static final String PASSWORD = "";

    private Database() {
    }

    static {
        try {
            Class.forName("com.mysql.cj.jdbc.Driver");
        } catch (ClassNotFoundException exception) {
            throw new ExceptionInInitializerError(
                    "MySQL Connector/J is missing from the runtime classpath. "
                            + "Add src/JDBC/mysql-connector-j-26.7.0.jar to the project dependencies.");
        }
    }

    public static Connection connect() throws SQLException {
        return DriverManager.getConnection(DATABASE_URL, USER, PASSWORD);
    }

    public static void initialize() throws SQLException {
        try (Connection connection = DriverManager.getConnection(SERVER_URL, USER, PASSWORD);
             Statement statement = connection.createStatement()) {
            statement.executeUpdate("CREATE DATABASE IF NOT EXISTS pharmacydb");
        }

        try (Connection connection = connect();
             Statement statement = connection.createStatement()) {
            statement.executeUpdate("CREATE TABLE IF NOT EXISTS users ("
                    + "user_id INT PRIMARY KEY AUTO_INCREMENT, username VARCHAR(50) NOT NULL UNIQUE,"
                    + "password VARCHAR(255) NOT NULL, role ENUM('Admin','Cashier') NOT NULL,"
                    + "full_name VARCHAR(100) NOT NULL, active BOOLEAN NOT NULL DEFAULT TRUE)");
            statement.executeUpdate("CREATE TABLE IF NOT EXISTS suppliers ("
                    + "supplier_id INT PRIMARY KEY AUTO_INCREMENT, name VARCHAR(100) NOT NULL,"
                    + "contact_person VARCHAR(100), phone VARCHAR(20), email VARCHAR(100), address TEXT)");
            statement.executeUpdate("CREATE TABLE IF NOT EXISTS medicines ("
                    + "medicine_id INT PRIMARY KEY AUTO_INCREMENT, name VARCHAR(150) NOT NULL,"
                    + "company VARCHAR(100) NOT NULL, medicine_type VARCHAR(50) NOT NULL, supplier_id INT,"
                    + "price DECIMAL(10,2) NOT NULL, quantity_in_stock INT NOT NULL DEFAULT 0,"
                    + "reorder_level INT NOT NULL DEFAULT 10, expiry_date DATE, "
                    + "FOREIGN KEY (supplier_id) REFERENCES suppliers(supplier_id) ON DELETE SET NULL)");
            statement.executeUpdate("CREATE TABLE IF NOT EXISTS sales ("
                    + "sale_id INT PRIMARY KEY AUTO_INCREMENT, sale_date TIMESTAMP NOT NULL DEFAULT CURRENT_TIMESTAMP,"
                    + "total_amount DECIMAL(10,2) NOT NULL, user_id INT NOT NULL,"
                    + "FOREIGN KEY (user_id) REFERENCES users(user_id))");
            statement.executeUpdate("CREATE TABLE IF NOT EXISTS sale_items ("
                    + "sale_item_id INT PRIMARY KEY AUTO_INCREMENT, sale_id INT NOT NULL, medicine_id INT NOT NULL,"
                    + "quantity_sold INT NOT NULL, price_at_sale DECIMAL(10,2) NOT NULL,"
                    + "FOREIGN KEY (sale_id) REFERENCES sales(sale_id) ON DELETE CASCADE,"
                    + "FOREIGN KEY (medicine_id) REFERENCES medicines(medicine_id))");
        }

        try (Connection connection = connect();
             PreparedStatement statement = connection.prepareStatement(
                     "INSERT IGNORE INTO users (username,password,role,full_name) VALUES (?,?,?,?)")) {
            addUserIfMissing(statement, "admin", "admin123", "Admin", "System Administrator");
            addUserIfMissing(statement, "cashier", "cashier123", "Cashier", "Demo Cashier");
        }
    }

    private static void addUserIfMissing(PreparedStatement statement, String username, String password,
                                         String role, String fullName) throws SQLException {
        statement.setString(1, username);
        statement.setString(2, hashPassword(password));
        statement.setString(3, role);
        statement.setString(4, fullName);
        statement.executeUpdate();
    }

    public static String hashPassword(String password) {
        try {
            MessageDigest digest = MessageDigest.getInstance("SHA-256");
            byte[] bytes = digest.digest(password.getBytes(StandardCharsets.UTF_8));
            StringBuilder result = new StringBuilder();
            for (byte value : bytes) {
                result.append(String.format("%02x", value));
            }
            return result.toString();
        } catch (NoSuchAlgorithmException exception) {
            throw new IllegalStateException("SHA-256 is not available", exception);
        }
    }

    public static User authenticate(String username, String password) throws SQLException {
        String sql = "SELECT user_id, username, role, full_name FROM users "
                + "WHERE username=? AND password=? AND active=TRUE";
        try (Connection connection = connect(); PreparedStatement statement = connection.prepareStatement(sql)) {
            statement.setString(1, username);
            statement.setString(2, hashPassword(password));
            try (ResultSet result = statement.executeQuery()) {
                if (result.next()) {
                    return new User(result.getInt("user_id"), result.getString("username"),
                            result.getString("role"), result.getString("full_name"));
                }
            }
        }
        return null;
    }
    public static final class User {
        public final int id;
        public final String username;
        public final String role;
        public final String fullName;

        public User(int id, String username, String role, String fullName) {
            this.id = id;
            this.username = username;
            this.role = role;
            this.fullName = fullName;
        }
    }
}
