package session7;

import java.sql.SQLException;
import java.util.Scanner;

public class MyApplication {
    public static void menu() {
        System.out.println("=========== Enter your choice =============");
        System.out.println("1. Search customer by Id");
        System.out.println("2. Add Customer");
        System.out.println("3. Update Customer");
        System.out.println("4. Delete Customer by Id");
        System.out.println("5. Show all Customer");
        System.out.println("6. Exit");
    }

    public static void main(String[] args) throws SQLException {
        Scanner sc = new Scanner(System.in);

        int choice;

        do {
            menu();
            choice = sc.nextInt();

            switch (choice) {
                case 1:
                    SearchById.searchById();
                    break;
                case 2:
                    InsertCustomers.insertCustomer();
                    break;
                case 3:
                    UpdateCustomers.updateCustomers();
                    break;
                case 4:
                    DeleteById.deleteById();
                    break;
                case 5:
                    GetAllCustomers.getAllCustomers();
                    break;
                case 6:
                    System.out.println("Exiting application...");
                    break;
                default:
                    System.out.println("Invalid choice. Please enter a number between 1 and 6.");
                    break;
            }
        } while (choice <= 6);

        sc.close(); // Đóng Scanner khi kết thúc chương trình
    }
}
