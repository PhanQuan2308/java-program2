package login.controller;

import login.entity.PasswordHasher;
import login.model.LoginDaoImpl;

import java.util.Scanner;

public class ControllerLogin {
    public static final Scanner sc = new Scanner(System.in);

    public static void controllerLogin() {
        try {
            System.out.println("Enter name: ");
            String nameLogin = sc.nextLine();

            System.out.println("Enter password: ");
            String passwordLogin = sc.nextLine();

            // Hash mật khẩu trước khi kiểm tra đăng nhập
            String hashedPassword = PasswordHasher.hashPassword(passwordLogin);

            // Kiểm tra đăng nhập
            LoginDaoImpl loginDao = new LoginDaoImpl();
            if (loginDao.loginUser(nameLogin, hashedPassword)) {
                System.out.println("Login success");
            } else {
                System.out.println("Login fail");
            }
        } catch (Exception e) {
            throw new RuntimeException(e);
        }
    }
}
