package JDBC;
import java.sql.*;

/**
 * StudentCRUD.java
 *
 * Demonstrates the four basic JDBC database operations (Create, Read,
 * Update, Delete) against a MySQL database running under XAMPP.
 *
 * SETUP BEFORE RUNNING:
 * 1. Start Apache and MySQL in the XAMPP control panel.
 * 2. Open phpMyAdmin (http://localhost/phpmyadmin) and run students.sql
 *    (or paste its contents into the SQL tab) to create the database/table.
 * 3. Download the MySQL Connector/J driver (the .jar file) from
 *    https://dev.mysql.com/downloads/connector/j/ and add it to your
 *    project's classpath / build path (in most IDEs: right-click project
 *    -> Build Path -> Add External JARs).
 * 4. Update DB_USER / DB_PASSWORD below to match your XAMPP MySQL login
 *    (XAMPP's default is user "root" with an EMPTY password).
 */
public class StudentCRUD {

    // --- Connection details for the local XAMPP MySQL server ---
    // "localhost:3306" -> MySQL's default host and port under XAMPP.
    // "studentdb"       -> the database created by students.sql.
    private static final String DB_URL  = "jdbc:mysql://localhost:3306/studentdb";
    private static final String DB_USER = "root";
    private static final String DB_PASSWORD = ""; // XAMPP default: no password

    public static void main(String[] args) {

        // 1. Load the driver and open ONE connection for the whole demo.
        //    try-with-resources automatically calls conn.close() for us
        //    when the block ends, even if an exception occurs.
        try (Connection conn = DriverManager.getConnection(DB_URL, DB_USER, DB_PASSWORD)) {

            System.out.println("Connected to MySQL successfully!\n");

            // 2. CREATE — insert a new student.
            createStudent(conn, "Sipho Dlamini", "Web Development", 20);

            // 3. READ — list every student currently in the table.
            System.out.println("--- All students ---");
            readStudents(conn);

            // 4. UPDATE — change one student's course by their id.
            //    (Assumes id 1 exists — adjust to a real id from your table.)
            updateStudentCourse(conn, 1, "Cloud Computing");

            // 5. READ again — see the update take effect.
            System.out.println("\n--- After update ---");
            readStudents(conn);

            // 6. DELETE — remove a student by id.
            //    (Adjust the id to one that actually exists before running.)
            deleteStudent(conn, 2);

            // 7. READ again — confirm the delete.
            System.out.println("\n--- After delete ---");
            readStudents(conn);

        } catch (SQLException e) {
            // Any problem talking to the database (bad URL, wrong password,
            // MySQL not running, etc.) lands here.
            System.out.println("Database error: " + e.getMessage());
        }
    }

    /**
     * CREATE: inserts one new row into the students table.
     * Uses a PreparedStatement so the values are safely escaped
     * (protects against SQL injection) instead of being concatenated
     * directly into the SQL string.
     */
    private static void createStudent(Connection conn, String name, String course, int age)
            throws SQLException {

        // The "?" characters are placeholders, filled in below in order (1, 2, 3...).
        String sql = "INSERT INTO students (name, course, age) VALUES (?, ?, ?)";

        // prepareStatement precompiles the SQL against the database.
        try (PreparedStatement stmt = conn.prepareStatement(sql)) {
            stmt.setString(1, name);   // fills the 1st "?" with the name
            stmt.setString(2, course); // fills the 2nd "?" with the course
            stmt.setInt(3, age);       // fills the 3rd "?" with the age

            // executeUpdate() is used for INSERT/UPDATE/DELETE — it returns
            // the number of rows affected, not a ResultSet.
            int rowsInserted = stmt.executeUpdate();
            System.out.println(rowsInserted + " student inserted: " + name);
        }
    }

    /**
     * READ: selects every row from the students table and prints it.
     */
    private static void readStudents(Connection conn) throws SQLException {

        String sql = "SELECT id, name, course, age FROM students";

        // Statement is fine here since there are no parameters to fill in.
        try (Statement stmt = conn.createStatement();
             // executeQuery() is used for SELECT — it returns a ResultSet.
             ResultSet rs = stmt.executeQuery(sql)) {

            // next() moves the cursor to the next row and returns false
            // when there are no more rows — this is how you loop over results.
            while (rs.next()) {
                int id       = rs.getInt("id");       // read column "id" as an int
                String name  = rs.getString("name");  // read column "name" as a String
                String course = rs.getString("course");
                int age      = rs.getInt("age");

                System.out.println(id + " | " + name + " | " + course + " | " + age);
            }
        }
    }

    /**
     * UPDATE: changes the "course" value for one student, identified by id.
     */
    private static void updateStudentCourse(Connection conn, int studentId, String newCourse)
            throws SQLException {

        String sql = "UPDATE students SET course = ? WHERE id = ?";

        try (PreparedStatement stmt = conn.prepareStatement(sql)) {
            stmt.setString(1, newCourse); // first "?" -> new course value
            stmt.setInt(2, studentId);    // second "?" -> which row to change

            int rowsUpdated = stmt.executeUpdate();
            System.out.println(rowsUpdated + " row(s) updated for student id " + studentId);
        }
    }

    /**
     * DELETE: removes one student row by id.
     */
    private static void deleteStudent(Connection conn, int studentId) throws SQLException {

        String sql = "DELETE FROM students WHERE id = ?";

        try (PreparedStatement stmt = conn.prepareStatement(sql)) {
            stmt.setInt(1, studentId);

            int rowsDeleted = stmt.executeUpdate();
            System.out.println(rowsDeleted + " row(s) deleted for student id " + studentId);
        }
    }
}
