package JDBC;

import java.sql.*;
//Create a database and table on myPHP admin

public class Students {
    private static final String DB_URL = "jdbc:mysql://localhost:3306/students";
    private static final String DB_USER = "root";
    private static final String DB_PASSWORD = "";

    public static void main(String[] args) {

        try (Connection conn = DriverManager.getConnection(DB_URL, DB_USER, DB_PASSWORD)) {
            System.out.println("Database Connection sucessfull");

            //create(conn, "Peter", 402413299, "Diploma in IT");

            read(conn);
        } catch (SQLException e) {
            System.out.println("System error: " + e.getMessage());
        }

    }

    //Prepared satements

    private static void create(Connection conn, String name, int studentNo, String course) throws SQLException{
        String sql = "INSERT INTO studentsTable (name, studentNo, course) VALUES (?, ?, ?)";
        
        try(PreparedStatement stmt = conn.prepareStatement(sql)){
            stmt.setString(1, name);
            stmt.setInt(2, studentNo);
            stmt.setString(3, course);

            int newRows = stmt.executeUpdate();
            System.out.println("New row created: " + newRows + " \nName: " + name);

        }
    }

    private static void read(Connection conn) throws SQLException{
        String sql = "SELECT * FROM studentsTable";

        try(Statement stmt = conn.createStatement(); 
        ResultSet rs = stmt.executeQuery(sql)){
            while (rs.next()){
                int id = rs.getInt("id");
                String name = rs.getString("name");
                int studentNo = rs.getInt("studentNo");
                String course = rs.getString("course");
                
                System.out.println("Id: " + id + "| Name: " + name + "| Student number: " + studentNo + " | Course: " + course + "." );
            };

        }
    }

}
