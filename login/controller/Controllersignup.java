package login.controller;

import login.entity.PasswordHasher;
import login.entity.Signup;
import login.model.SignupDaoImpl;

import java.util.Scanner;



    public class Controllersignup {
        public static final Scanner sc = new Scanner(System.in);

        public static void controllersignup() {
            try {
                System.out.println("Enter name: ");
                String nameSign = sc.nextLine();

                String emailSign;
                do {
                    System.out.println("Enter email: ");
                    emailSign = sc.nextLine();
                    if (!emailSign.endsWith("@gmail.com")) {
                        System.out.println("Email must end @gmail.com");
                    } else {
                        break;
                    }
                } while (true);

                System.out.println("Enter password: ");
                String passwordSign = sc.nextLine();

                System.out.println("Confirm password: ");
                String confirmPass = sc.nextLine();

                if (!passwordSign.equals(confirmPass)) {
                    System.out.println("Passwords do not match");
                    return;
                }

                String hashedPassword = PasswordHasher.hashPassword(passwordSign); // Hash password

                Signup signup = new Signup(nameSign, emailSign, hashedPassword); // Store hashed password
                SignupDaoImpl signupDao = new SignupDaoImpl();
                if (!signupDao.signupUser(signup)) {
                    System.out.println("Sign up fail");
                } else {
                    System.out.println("Sign up success");
                }
            } catch (Exception e) {
                System.out.println("Error occurred: " + e.getMessage());
            }
        }
    }



