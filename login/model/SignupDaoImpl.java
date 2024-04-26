package login.model;

import login.dao.DbConnectionSignup;
import login.entity.Signup;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.SQLException;

public class SignupDaoImpl {

    private final Connection conn = DbConnectionSignup.getConnectionSignUp();

    public SignupDaoImpl() throws SQLException {
    }

    public boolean signupUser(Signup signup) {
        try  {
            String SQL_INSERT_SIGNUP = "INSERT INTO signup (username, email, password) VALUES (?, ?, ?)";
            try (PreparedStatement preparedStatement = conn.prepareStatement(SQL_INSERT_SIGNUP)) {
                preparedStatement.setString(1, signup.getUserName());
                preparedStatement.setString(2, signup.getEmail());
                preparedStatement.setString(3, signup.getPassword());

                int rs = preparedStatement.executeUpdate();
                if(rs>0){
                    System.out.println("signup success");
                }else {
                    System.out.println("signup fail");
                }
            }
        } catch (Exception e) {
            System.out.println("Error occurred during sign up: " + e.getMessage());

        }
        return false;
    }

}
