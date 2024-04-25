package sessionlogin;

public class Main {
    public static void main(String[] args) {
        // Gọi phương thức signUp để đăng ký người dùng mới
        Controller.signUp();

        // Sau khi đã đăng ký, gọi phương thức login để đăng nhập
        Controller.login();
    }
}
