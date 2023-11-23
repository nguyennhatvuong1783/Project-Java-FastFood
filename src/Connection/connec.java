package Connection;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.SQLException;

public class connec {

    public static Connection getConnection1() {
        Connection connection = null;
        String URL = "jdbc:sqlserver://localhost:1433;databaseName=THUCANNHANH;encrypt=true;trustServerCertificate=true";

        try {
            Class.forName("com.microsoft.sqlserver.jdbc.SQLServerDriver");
            connection = DriverManager.getConnection(URL, "sa", "jhin");
            System.out.println("[getConnection]: Connected.");
        } catch (ClassNotFoundException | SQLException e) {
            System.out.println("Can't connect.");
            System.out.println("Erroe: " + e);
        }

        return connection;
    }
    
    public static Connection getConnection() {
        Connection connection = null;
        try {
            Class.forName("com.microsoft.sqlserver.jdbc.SQLServerDriver");
            connection = DriverManager.getConnection("jdbc:sqlserver://localhost:1433;databaseName=THUCANNHANH;user=sa;password=123456;encrypt=false");
        } catch (ClassNotFoundException | SQLException e) {
            // TODO: handle exception
            e.printStackTrace();
        }
        return connection;

    }

    public static void closeConnection(Connection c) {
        try {
            if (c != null) {
                c.close();
            }
        } catch (Exception e) {
            // TODO: handle exception
            e.printStackTrace();
        }
    }

    public static void main(String[] args) {
        Connection connection = getConnection();
        System.out.println(connection);

        closeConnection(connection);
    }

}
