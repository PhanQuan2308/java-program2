package login.dao;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.SQLException;

public class DbConnectionSignup {
    public  static Connection getConnectionSignUp() throws SQLException {
        Connection conn = null;
        String dbURL = "jdbc:mysql://localhost:3306/login";
        conn = DriverManager.getConnection(dbURL, "root","");
        return conn;
    }

    public static void main(String[] args) throws  SQLException{
        if(getConnectionSignUp()!= null){
            System.out.println("Connect success");
        }
    }
}
