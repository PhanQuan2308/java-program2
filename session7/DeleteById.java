package session7;

import java.sql.Connection;
import java.sql.SQLException;
import java.sql.Statement;
import java.util.Scanner;

public class DeleteById {
    public static void deleteById() throws SQLException {
        Scanner sc = new Scanner(System.in);

        Connection conn = MySQLConnectDB.getMySqlConnection();
        Statement stm = conn.createStatement();

        System.out.println("Delete customer by Id: ");
        int deleteCus = sc.nextInt();

        String deleteQuery = "DELETE FROM customers WHERE customer_id = " + deleteCus;
        int rowAffected = stm.executeUpdate(deleteQuery);

        if (rowAffected > 0) {
            System.out.println("Customer found with Id: " + deleteCus);
            System.out.println("Do you want to delete? Y: yes, N: no ");
            String confirm = sc.next();

            if (confirm.equalsIgnoreCase("Y")) {
                System.out.println("Delete Success!");
            } else {
                System.out.println("Canceled Delete!");
            }
        } else {
            System.out.println("No customer found with Id: " + deleteCus);
        }

        // Đóng các tài nguyên
        stm.close();
        conn.close();
        sc.close();
    }
}
