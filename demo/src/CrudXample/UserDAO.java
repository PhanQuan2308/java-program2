package CrudXample;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;

public class UserDAO {


    private static final String INSERT_USER_SQL = "INSERT INTO  users " + " (name, email,country) VALUES " + " (?,?,?);";
    private static final String SELECT_USER_BY_ID = "SELECT id, name, email, country from users where id  = ?";
    private static final String SELECT_ALL_USERS = "SELECT * FROM users";
    private static  final String DELETE_USERS_SQL = "DELETE FROM users where id = ?; ";
    private static final  String UPDATE_USERS_SQL = "update users set name = ?, email = ?, country = ? where id = ?;";

    public  void  insertUser(Users users) throws SQLException{
        try {
            Connection connection = DbConnection.getConnection();
            PreparedStatement preparedStatement = connection.prepareStatement(INSERT_USER_SQL);
            preparedStatement.setString(1, users.getName());
            preparedStatement.setString(2,users.getEmail());
            preparedStatement.setString(3,users.getCountry());

            preparedStatement.executeUpdate();
        }catch (SQLException e){
            e.printStackTrace();
        }
    }
    public boolean updateUser(Users users) throws SQLException {
        boolean rowUpdated = false;
        try {
            Connection connection = DbConnection.getConnection();
            PreparedStatement preparedStatement = connection.prepareStatement(UPDATE_USERS_SQL);
            preparedStatement.setString(1, users.getName());
            preparedStatement.setString(2, users.getEmail());
            preparedStatement.setString(3, users.getCountry());
            preparedStatement.setInt(4, users.getId()); // Đặt giá trị cho tham số thứ tư

            rowUpdated = preparedStatement.executeUpdate() > 0;
        } catch (SQLException e) {
            e.printStackTrace();
        }
        return rowUpdated;
    }


    public Users selectUser(int id){
        Users user = null;
        try {
            Connection connection = DbConnection.getConnection();
            PreparedStatement preparedStatement = connection.prepareStatement(SELECT_USER_BY_ID);
            preparedStatement.setInt(1, id);
            ResultSet rs = preparedStatement.executeQuery();
            while (rs.next()){
                String name = rs.getString("name");
                String email = rs.getString("email");
                String country = rs.getString("country");


                user = new Users(id,name,email,country);
            }
        }catch (SQLException e){
            e.printStackTrace();
        }
        return user;
    }

    public List<Users> selectAllUser(){
        List<Users> users =  new ArrayList<>();
        try {
            Connection connection = DbConnection.getConnection();
            PreparedStatement preparedStatement = connection.prepareStatement(SELECT_ALL_USERS);
            ResultSet rs = preparedStatement.executeQuery();
            while (rs.next()){
                int id = rs.getInt("id");
                String name = rs.getString("name");
                String email = rs.getString("email");
                String country = rs.getString("country");


                users.add(new Users(id,name,email,country));
            }
        }catch (SQLException e){
            e.printStackTrace();
        }
        return users;
    }
    public boolean deleteUser(int id) throws SQLException{
        boolean rowDeleted = false;

        try {
            Connection connection = DbConnection.getConnection();
            PreparedStatement preparedStatement = connection.prepareStatement(DELETE_USERS_SQL);
            preparedStatement.setInt(1, id);
            rowDeleted = preparedStatement.executeUpdate() > 0;


        }catch (SQLException e){
            e.printStackTrace();
        }
        return  rowDeleted;
    }
}
