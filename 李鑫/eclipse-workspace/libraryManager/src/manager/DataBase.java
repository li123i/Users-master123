package manager;

import java.sql.*;

class DataBase {

    private static Connection conn = null;

    static {
        try {
            Class.forName("com.mysql.cj.jdbc.Driver"); //加载MYSQL JDBC驱动程序 
            conn = DriverManager.getConnection("jdbc:mysql://139.199.203.77:3306/t1?useSSL=true","root","root");
          //连接URL为   jdbc:mysql//服务器地址/数据库名  ，后面的2个参数分别是登陆用户名和密
        } catch (ClassNotFoundException | SQLException e) {
            e.printStackTrace();
        }
    }

    static Connection getUserConnection() {
        return conn;
    }

}