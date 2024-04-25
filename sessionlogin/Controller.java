package sessionlogin;

import java.sql.Connection;
import java.sql.SQLException;
import java.util.Scanner;

public class Controller {
    public static void signUp() {
        Scanner sc = new Scanner(System.in);
        System.out.println("======Sign up=========== ");
        System.out.println("Name: ");
        String nameSign = sc.nextLine();

        System.out.println("Email: ");
        String emailSign = sc.nextLine();

        System.out.println("Password: ");
        String passwordSign = sc.nextLine();

        Signup.registerUser( nameSign, emailSign, passwordSign);
    }

    public static void login() {
        Scanner sc = new Scanner(System.in);
        System.out.println("======Login=============");
        System.out.println("Name: ");
        String nameLogin = sc.nextLine();

        System.out.println("Password: ");
        String passLogin = sc.nextLine();

        int userId = Login.loginUser(nameLogin, passLogin);
        if (userId != -1) { // Kiểm tra xem đăng nhập thành công
            System.out.println("User logged in successfully!");
            // Hiển thị giỏ hàng của người dùng sau khi đăng nhập thành công
            ShoppingCart.displayUserCart(userId);
        } else {
            System.out.println("Login failed!");
        }
    }


}
