package sessionlogin;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;

public class Login {
    private static final String SELECT_USER_SQL = "SELECT user_id FROM signup WHERE name = ? AND password = ?";

    static int loginUser(String name, String password) {
        try {
            Connection conn = DbConnection.getConnection();
            PreparedStatement preparedStatement = conn.prepareStatement(SELECT_USER_SQL);
            preparedStatement.setString(1, name);
            preparedStatement.setString(2, password);
            ResultSet rs = preparedStatement.executeQuery();

            if (rs.next()) {
                int userId = rs.getInt("user_id");
                System.out.println("Login success");
                conn.close();
                return userId;
            } else {
                System.out.println("Login fail: Invalid name or password");
                conn.close();
                return -1; // Đăng nhập thất bại, trả về giá trị không hợp lệ
            }
        } catch (Exception e) {
            throw new RuntimeException(e);
        }
    }
}

