package Connection;

import java.sql.Connection;
import java.sql.DriverManager;

public class DBConnect {
    private static String DB_URL = "jdbc:mysql://localhost:3306/QLCB?useSSL=false";
    private static String userName = "root";
    private static String passWord = "minhtien01";

    public static Connection getConnection() {
        Connection connection = null;
        try {
            Class.forName("com.mysql.cj.jdbc.Driver");
            connection = DriverManager.getConnection(DB_URL, userName, passWord);
        } catch (Exception e) {
            e.printStackTrace();
        }
        return connection;
    }
}
