package sessionlogin;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.SQLException;

public class Signup {

    private static final String INSERT_SIGNUP_SQL = "INSERT INTO signup " + "(name, email, password) VALUES (?, ?, ?)";

    public static void registerUser( String name, String email, String password) {
        try {
            Connection conn = DbConnection.getConnection();
            PreparedStatement preparedStatement = conn.prepareStatement(INSERT_SIGNUP_SQL);
            preparedStatement.setString(1, name);
            preparedStatement.setString(2, email);
            preparedStatement.setString(3, password);
            preparedStatement.executeUpdate();
            System.out.println("User signup successfully");
        } catch (SQLException e) {
            e.printStackTrace();
        }
    }
}
