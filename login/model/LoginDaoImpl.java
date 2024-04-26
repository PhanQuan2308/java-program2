package login.model;

import login.dao.DbConnectionSignup;
import login.entity.Signup;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;

public class LoginDaoImpl {

    public static Connection conn;

    static {
        try {
            conn = DbConnectionSignup.getConnectionSignUp();
        } catch (SQLException e) {
            throw new RuntimeException(e);
        }
    }

    public boolean loginUser(String username, String password) {
        try {
            String SQL_SELECT_LOGIN = "SELECT * FROM signup WHERE username = ? AND password = ?";
            try (PreparedStatement preparedStatement = conn.prepareStatement(SQL_SELECT_LOGIN)) {
                preparedStatement.setString(1, username);
                preparedStatement.setString(2, password);
                try (ResultSet resultSet = preparedStatement.executeQuery()) {
                    return resultSet.next(); // Trả về true nếu có người dùng tồn tại với tên người dùng và mật khẩu đã cung cấp
                }
            }
        } catch (Exception e) {
            throw new RuntimeException("Error occurred during login: " + e.getMessage());
        }
    }
}
