package session7;

import java.sql.Connection;
import java.sql.SQLException;
import java.sql.Statement;
import java.util.Scanner;

public class UpdateCustomers {
    public static void menuUpdate() {
        System.out.println("1. Update first name");
        System.out.println("2. Update last name");
        System.out.println("3. Update email");
    }

    public static void updateCustomers() throws SQLException {
        Scanner sc = new Scanner(System.in);

        System.out.println("Enter the ID of the customer you want to update: ");
        int updateId = sc.nextInt();
        System.out.println("Which Customer ID do you want to update? " + updateId);

        Connection conn = MySQLConnectDB.getMySqlConnection();
        Statement stm = conn.createStatement();

        // Kiểm tra xem khách hàng có tồn tại hay không
        String checkExistsQuery = "SELECT * FROM customers WHERE customer_id = " + updateId;
        if (stm.executeQuery(checkExistsQuery).next()) {
            boolean continueUpdating = true;
            do {
                System.out.println("What do you want to update?");
                menuUpdate();
                int choice = sc.nextInt();
                switch (choice) {
                    case 1:
                        System.out.println("Enter the new first name: ");
                        String newFirstName = sc.next();
                        String updateQuery = "UPDATE customers SET first_name = '" + newFirstName + "' WHERE customer_id = " + updateId;
                        stm.executeUpdate(updateQuery);
                        System.out.println("First name updated successfully!");
                        break;
                    case 2:
                        System.out.println("Enter the new last name: ");
                        String newLastName = sc.next();
                        updateQuery = "UPDATE customers SET last_name = '" + newLastName + "' WHERE customer_id = " + updateId;
                        stm.executeUpdate(updateQuery);
                        System.out.println("Last name updated successfully!");
                        break;
                    case 3:
                        System.out.println("Enter the new email: ");
                        String newEmail = sc.next();
                        updateQuery = "UPDATE customers SET email = '" + newEmail + "' WHERE customer_id = " + updateId;
                        stm.executeUpdate(updateQuery);
                        System.out.println("Email updated successfully!");
                        break;
                    default:
                        System.out.println("Invalid option. Please enter a number between 1 and 3.");
                        break;
                }

                System.out.println("Do you want continue to update? (Y/N)");
                String continueOption = sc.next();
                if (!continueOption.equalsIgnoreCase("Y")) {
                    continueUpdating = false;
                }
            } while (continueUpdating);
        } else {
            System.out.println("No customer found with ID: " + updateId);
        }

        // Đóng các tài nguyên
        stm.close();
        conn.close();
        sc.close();
    }



}
