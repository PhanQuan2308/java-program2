package session10.ui;

import session10.controller.ControllerProduct;

import java.sql.SQLException;
import java.util.Scanner;

public class ViewProduct {
    public static  void menu(){
        System.out.println("===========Product Management============");
        System.out.println("1.Add product.  ");
        System.out.println("2.Search product by Id.");
        System.out.println("3.Show all product.");
        System.out.println("4.Update product.");
        System.out.println("5.Delete product.");
        System.out.println("0.Exit");
    }

    public static void main(String[] args) throws SQLException {

        Scanner sc = new Scanner(System.in);
        int choice;

        do{
            menu();
            System.out.println("Enter your choice");
            choice = sc.nextInt();
             switch (choice){
                case 1:
                    ControllerProduct.createProduct();
                    break;
                case 2:
                    ControllerProduct.getProductById();
                    break;
                case 3:
                    ControllerProduct.getAllProduct();
                    break;
                case 4:
                    ControllerProduct.updateProduct();
                    break;
                case 5:
                    ControllerProduct.deleteProduct();
                    break;
                case 0:
                    System.out.println("Exit");
                default:
                    System.out.println("Invalid");
            }
        }while (choice != 0);
    }
}
