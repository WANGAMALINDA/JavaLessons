# Windows package

Run `package-windows.ps1` from PowerShell to create:

```text
dist\Wanga_Malinda_pims-1.0.0.exe
```

The installer bundles the Java runtime, application classes, and MySQL
Connector/J driver. It does **not** bundle MySQL Server itself. Start MySQL in
XAMPP before launching the installed application. The application connects to
`jdbc:mysql://localhost:3306/pharmacydb` using the default XAMPP credentials
(`root` with an empty password).

The first application launch creates the `pharmacydb` database and its tables.
Default demo accounts are:

| Role | Username | Password |
| --- | --- | --- |
| Administrator | `admin` | `admin123` |
| Cashier | `cashier` | `cashier123` |
