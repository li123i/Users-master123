package manager;

import java.sql.*;

class DataBase {

    private static Connection conn = null;

    static {
        try {
            Class.forName("com.mysql.cj.jdbc.Driver");
            conn = DriverManager.getConnection("jdbc:mysql://139.199.203.77:3306/t1?useSSL=true","root","root");
        } catch (ClassNotFoundException | SQLException e) {
            e.printStackTrace();
        }
    }

    static Connection getUserConnection() {
        return conn;
    }

}