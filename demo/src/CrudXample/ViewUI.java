package CrudXample;

import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;
import java.util.Scanner;

public class ViewUI {
    public static void menu(){
        System.out.println("==========CRUD USER============");
        System.out.println("1. Add User.");
        System.out.println("2. Show all user");
        System.out.println("3. Delete user by Id");
        System.out.println("4. Update user by Id");
        System.out.println("0. Exit");
    }

    public static void  AddUser() {
        Scanner sc = new Scanner(System.in);

        System.out.printf("Enter name: ");
        String addName = sc.nextLine();

        System.out.printf("Enter email: ");
        String addEmail = sc.nextLine();

        System.out.printf("Enter Country: ");
        String addCountry = sc.nextLine();

        Users users = new Users(0, addName,addEmail,addCountry);
        UserDAO userDAO = new UserDAO();
        try {
            userDAO.insertUser(users);
            System.out.println("Add success");
        }catch (SQLException e){
            System.out.println("Add Fail" + e.getMessage());
        }

    }
    public static void EditUser(){
        Scanner sc = new Scanner(System.in);
        System.out.println("Enter id user edit: ");
        int userId = Integer.parseInt(sc.nextLine());

        UserDAO userDAO = new UserDAO();
        Users usersToEdit = userDAO.selectUser(userId);

        if(usersToEdit != null){
            System.out.println("User Information:");
            System.out.println("Id user: " + usersToEdit.getId());
            System.out.println("Name user: " + usersToEdit.getName());
            System.out.println("Email User: " + usersToEdit.getEmail());
            System.out.println("Contry User: " + usersToEdit.getCountry());

            System.out.println("Enter new name: ");
            String newName = sc.nextLine();
            System.out.println("Enter new email: ");
            String newEmail = sc.nextLine();
            System.out.println("Enter new country: ");
            String newCountry = sc.nextLine();

            try {
                // Cập nhật thông tin người dùng với dữ liệu mới
                usersToEdit.setName(newName);
                usersToEdit.setEmail(newEmail);
                usersToEdit.setCountry(newCountry);
                boolean isUpdated = userDAO.updateUser(usersToEdit);
                if(isUpdated){
                    System.out.println("Update Successfully!");
                }else {
                    System.out.println("Update fail!");
                }
            } catch (SQLException e) {
                throw new RuntimeException(e);
            }
        } else {
            System.out.println("Not found user with id  " + userId);
        }
    }

    public static  void ShowAllUser(){
        UserDAO userDAO = new UserDAO();
        List<Users> users = userDAO.selectAllUser();
        if(!users.isEmpty()){
            System.out.println("All Users: ");
            for (Users user : users){
                System.out.println("Id: " + user.getId());
                System.out.println("Name: " + user.getName());
                System.out.println("Email: " + user.getEmail());
                System.out.println("Country: " + user.getCountry());
            }
        }else {
            System.out.println("No Users in database");
        }
    }

    public static void removeUSer(){
        Scanner sc = new Scanner(System.in);
        System.out.println("Enter id user to delete: ");
        int removeUserId = Integer.parseInt(sc.nextLine());
        UserDAO userDAO = new UserDAO();
        Users removeId = userDAO.selectUser(removeUserId);

        if(removeId != null){
            System.out.println("Information user: ");
            System.out.println("Id: " + removeId.getId());
            System.out.println("Name: " + removeId.getName());
            System.out.println("Email: " + removeId.getEmail());
            System.out.println("Country: " + removeId.getCountry());

            System.out.println("Are you sure? (Y/N)");
            String confirm = sc.nextLine();
            if(confirm.equalsIgnoreCase("Y")){
                // Thực hiện xóa người dùng
                try {
                    boolean deleted = userDAO.deleteUser(removeUserId);
                    if (deleted) {
                        System.out.println("Deleted user!");
                    } else {
                        System.out.println("Delete failure!");
                    }
                } catch (SQLException e) {
                    e.printStackTrace();
                }
            } else {
                System.out.println("Delete canceled!");
            }
        } else {
            System.out.println("Not found user with Id " + removeUserId + " to delete");
        }
    }

    public static void main(String[] args) {
        Scanner sc = new Scanner(System.in);
        int choice;
        do{
            menu();
            System.out.println("Enter your choice: ");
            choice = Integer.parseInt(sc.nextLine());
            switch (choice){
                case 1:
                    AddUser();
                    break;
                case 2:
                    ShowAllUser();
                    break;
                case 3:
                    removeUSer();
                    break; // Thêm break vào đây để thoát khỏi switch case sau khi xóa người dùng
                case 4:
                    EditUser();
                    break;
                case 0:
                    System.out.println("End!");
                    break;
                default:
                    System.out.println("Enter invalid!");
            }
        }while (choice != 0);
    }
}
