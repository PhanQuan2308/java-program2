package login.view;

import login.controller.ControllerLogin;
import login.controller.Controllersignup;
import session8.controller.LoginController;

import java.util.Scanner;

public class UiLogin {
    public static void menu(){
        System.out.println("===========Enter your choice ============");
        System.out.println("1.Login");
        System.out.println("2.Signup");
        System.out.println("0.Exit");
    }

    public static void main(String[] args) {
        Scanner sc = new Scanner(System.in);
        int choice;

        do{
            menu();
            choice = sc.nextInt();
            switch (choice){
                case 1:
                    ControllerLogin.controllerLogin();
                    break;
                case 2:
                    Controllersignup.controllersignup();
                    break;
                case 0:
                    System.out.println("Exiting....");
                    break;
                default:
                    System.out.println("Invalid choice! Please enter again.");

            }
        }while (choice !=0 );
    }
}
