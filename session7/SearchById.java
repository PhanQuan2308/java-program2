package session7;

import java.sql.Connection;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;
import java.util.Scanner;

public class SearchById {
    public static void searchById() throws SQLException{
        Connection conn = MySQLConnectDB.getMySqlConnection();
        Statement stm = conn.createStatement();

        Scanner sc = new Scanner(System.in);

        String searchById = sc.nextLine();
        System.out.println("Id you want search: " + searchById);

        String searchByIdQuery = "SELECT * from customers WHERE id = " + searchById;

        ResultSet rs = stm.executeQuery(searchByIdQuery);

        if(rs.next()){
            int customerId = rs.getInt(1);
            String fn = rs.getNString(2);
            String ln = rs.getNString(3);
            String email = rs.getNString(4);

            System.out.println("Customer ID: " + customerId);
            System.out.println("First Name: " + fn);
            System.out.println("Last Name: " + ln);
            System.out.println("Email: " + email);
        }else {
            System.out.println("No customer found with ID: " + searchById);
        }

        rs.close();
        stm.close();
        conn.close();

    }
}
