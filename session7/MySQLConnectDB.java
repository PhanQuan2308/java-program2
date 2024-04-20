package session7;

import java.sql.Connection;
import java.sql.Driver;
import java.sql.DriverManager;
import java.sql.SQLException;

public class MySQLConnectDB {
    public  static Connection getMySqlConnection() throws SQLException {
        Connection conn = null;
        String hostName = "localhost";//127.0.0.1
        String dbName = "employeeFpt";
        String userName = "root";
        String password = "";

        //String connection(chuoi ket noi)
        String connectionURL = "jdbc:mysql://" + hostName + ":3306/" + dbName;
        conn = DriverManager.getConnection(connectionURL, userName, password);
        return conn;

    }


}
