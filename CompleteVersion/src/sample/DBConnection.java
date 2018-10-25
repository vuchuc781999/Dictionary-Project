package sample;

import java.sql.*;

public class DBConnection {
    private static final String url = "jdbc:mysql://localhost:3306/dictionary";
    private static final String username = "root";
    private static final String password = "123456";

    public static Connection connection;

    static {
        connection = null;
    }

    public static void connect() {
        try {
            Class.forName("com.mysql.cj.jdbc.Driver");
            connection = DriverManager.getConnection(url, username, password);
        } catch (Exception e) {
            e.printStackTrace();
        }
    }

    public static void disconnect() {
        try {
            connection.close();
        } catch (Exception e) {
            e.printStackTrace();
        }
    }
}
