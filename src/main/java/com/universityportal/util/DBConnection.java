package main.java.com.universityportal.util;
import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.SQLException;


public class DBConnection {

    private static final String USERNAME="postgres";
    private static final String PASSWORD="chill";
    private static final String URL="jdbc:postgresql://localhost:5432/university_db";

    private static Connection connection = null;

    private DBConnection() {}

    public static Connection getConnection() throws SQLException {
       return DriverManager.getConnection(URL,USERNAME,PASSWORD);
    }

    public static void closeConnection() {
        try {
            if (connection != null && !connection.isClosed()) {
                connection.close();
            }
        } catch (SQLException e) {
            e.printStackTrace();
        }
    }
}
