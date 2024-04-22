package HopitalManagementSystem;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.Scanner;

public class Doctor {
    private Connection connection;

    private Scanner scanner;

    public Doctor() {
        this.connection = connection;
        this.scanner = scanner;
    }

    public  void viewDoctors(){
        String query = "SELECT * FROM doctors";
        try {
            PreparedStatement preparedStatement = connection.prepareStatement(query);
            ResultSet resultSet = preparedStatement.executeQuery();

            System.out.println("Doctors: ");
            System.out.println("============+=================+====================");
            System.out.println("Doctors Id  | Name            | specialization      ");
            System.out.println("============+=================+====================");

            while (resultSet.next()){
                int id = resultSet.getInt(1);
                String name = resultSet.getNString(2);
                String specialization = resultSet.getNString(3);


                System.out.printf("|%-12s|%-20s|%-10s|\n", id, name, specialization);
                System.out.println("============+==================+=================");
            }
        }catch (SQLException e){
            e.printStackTrace();
        }
    }
    public boolean getDoctorById(int id){
        String query = "SELECT * FROM doctors WHERE id = ?";
        try {
            PreparedStatement preparedStatement = connection.prepareStatement(query);
            preparedStatement.setInt(1, id);
            ResultSet rs = preparedStatement.executeQuery();

            if(rs.next()){
                return  true;
            }
            else {
                return  false;
            }

        }catch (SQLException e){
            e.printStackTrace();
        }
        return false;
    }
}
