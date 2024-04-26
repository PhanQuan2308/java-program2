// ViewOrder.java
package session10.ui;

import session10.controller.ControllerOrder;

import java.sql.SQLException;
import java.util.Scanner;

public class ViewOrder {
    public static void showMenu() {
        Scanner sc = new Scanner(System.in);
        int choice;

        do {
            System.out.println("===========Order Management============");
            System.out.println("1. Add order");
            System.out.println("2. Search order by Id");
            System.out.println("3. Show all orders");
            System.out.println("4. Update order");
            System.out.println("5. Delete order");
            System.out.println("0. Exit");
            System.out.println("Enter your choice");
            choice = sc.nextInt();
            switch (choice) {
                case 1:
                    ControllerOrder.creatOrder();
                    break;
                case 2:
                    ControllerOrder.getOrderById();
                    break;
                case 3:
                    ControllerOrder.getAllOrder();
                    break;
                case 4:
                    ControllerOrder.updateOrder();
                    break;
                case 5:
                    ControllerOrder.deleteOrder();
                    break;
                case 0:
                    System.out.println("Exit Order Management");
                    break;
                default:
                    System.out.println("Invalid choice");
            }
        } while (choice != 0);
    }
}
