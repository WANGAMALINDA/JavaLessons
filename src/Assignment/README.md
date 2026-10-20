# Pharmacy Management System

This Swing/JDBC application uses MySQL through the Connector/J jar already in
the project. Start MySQL in XAMPP before launching the application. The
application creates the `pharmacydb` database and its tables on first startup.

## Login accounts

| Role | Username | Password |
| --- | --- | --- |
| Administrator | `admin` | `admin123` |
| Cashier | `cashier` | `cashier123` |

Change the default passwords after demonstrating the application. Passwords
are stored as SHA-256 hashes, and all user-entered SQL values use prepared
statements.

## Features

- Role-based login and redirection.
- The interface is divided into page components in the `pages` package:
  `LoginPage`, `AdminDashboardPage`, `CashierDashboardPage`,
  `MedicinePage`, `SupplierPage`, `UserPage`, `PosPage`, `StockPage`, and
  `ReportPage`.
- Each page owns its Swing controls, event handlers, database queries, and
  validation logic. `PharmacyApplication` only initializes the database and
  opens the login page.
- Administrators can manage medicines, suppliers, and cashier accounts.
- Cashiers can check stock, create sales, deduct stock transactionally, and
  save a bill as `bill-<sale number>.txt`.
- Administrators can view stock, sales totals, low-stock medicines, and top
  selling medicines, and print the report.

Run `Assignment.PharmacyApplication` from IntelliJ with
`src/JDBC/mysql-connector-j-26.7.0.jar` on the module classpath.

If the application reports `No suitable driver found`, the Connector/J jar is
on the compile path but not the runtime path. In IntelliJ, open
**File > Project Structure > Modules > Dependencies**, click **+ > JARs or
directories**, select
`src/JDBC/mysql-connector-j-26.7.0.jar`, choose **Compile**, and apply the
changes. In VS Code, run the application with the jar on the classpath, for
example:

```text
java -cp "out;src/JDBC/mysql-connector-j-26.7.0.jar" Assignment.PharmacyApplication
```
