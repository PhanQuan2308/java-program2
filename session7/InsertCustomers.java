package session7;

import java.sql.Connection;
import java.sql.SQLException;
import java.sql.Statement;
import java.util.Scanner;

public class InsertCustomers {
    private static boolean isValidName(String name) {
        // Kiểm tra xem tên có chứa ký tự đặc biệt hoặc số không
        return !name.matches(".*[!@#$%^&*()_+=\\[\\]{};':\"\\\\|,.<>\\/?0-9].*");
    }
    private static boolean isValidEmail(String email) {
        return email.endsWith("@gmail.com");
    }
    public static void insertCustomer() throws SQLException {

        Scanner sc = new Scanner(System.in);

        String insertFn, insertLn, insertEmail;

        do{
            System.out.println("- Add first name: " );
            insertFn = sc.nextLine();
        }while (!isValidName(insertFn));

        do{
            System.out.println("- Add last name: " );
            insertLn = sc.nextLine();
        }while (!isValidName(insertLn));

        do{
            System.out.println("- Add email: " );
            insertEmail = sc.nextLine();
        }while (!isValidEmail(insertEmail));


        Connection conn = MySQLConnectDB.getMySqlConnection();
        Statement stm = conn.createStatement();



        String insertQuery = "INSERT INTO customers (first_name, last_name, email) VALUES ('"+  insertFn +"', '"+ insertLn+"', '"+ insertEmail +"')";

        int rowsAffected = stm.executeUpdate(insertQuery);
        if(rowsAffected > 0){
            System.out.println("Add success!");
        }else{
            System.out.println("Add fail!");
        }
        stm.close();
        conn.close();
        sc.close();
    }

}