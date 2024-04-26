package session10.ui;

import session10.entity.Product;

import java.util.Scanner;

public class MainMenu {
    public static void menu() {
        System.out.println("===========Main Menu============");
        System.out.println("1. Product Management");
        System.out.println("2. Order Management");
        System.out.println("0. Exit");
    }

    public static void main(String[] args) {
        Scanner sc = new Scanner(System.in);
        int choice;

        do {
            menu();
            System.out.println("Enter your choice");
            choice = sc.nextInt();
            switch (choice) {
                case 1:
                    ViewProduct.showMenu();
                    break;
                case 2:
                    ViewOrder.showMenu();
                    break;
                case 0:
                    System.out.println("Exit");
                    break;
                default:
                    System.out.println("Invalid choice");
            }
        } while (choice != 0);
    }
    }

