package HopitalManagementSystem;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.Scanner;

public class Patient {
    private Connection connection;

    private  Scanner scanner;

    public Patient(Connection connection, Scanner scanner) {
        this.connection = connection;
        this.scanner = scanner;
    }

    public void addPatient() {
        System.out.println("Enter Patient Name: ");
        scanner.nextLine(); // Tiêu thụ dấu xuống dòng còn lại trong bộ đệm
        String name = scanner.nextLine();

        System.out.println("Enter Patient Age: ");
        String age = scanner.nextLine();

        System.out.println("Enter Patient Gender: ");
        String gender = scanner.nextLine();

        try {
            String query = "INSERT INTO patients(name, age, gender) VALUES(?, ?, ?)";
            PreparedStatement preparedStatement = connection.prepareStatement(query);
            preparedStatement.setString(1, name);
            preparedStatement.setString(2, age);
            preparedStatement.setString(3, gender);

            int affectedRows = preparedStatement.executeUpdate();
            if (affectedRows > 0) {
                System.out.println("Patient added successfully!");
            } else {
                System.out.println("Failed to add Patient!");
            }

        } catch (SQLException e) {
            e.printStackTrace();
        }
    }



    public  void viewPatients(){
        String query = "SELECT * FROM patients";
        try {
            PreparedStatement preparedStatement = connection.prepareStatement(query);
            ResultSet resultSet = preparedStatement.executeQuery();

            System.out.println("Patients: ");
            System.out.println("============+=================+=============+===================");
            System.out.println("Patients Id | Name            | Age         | Gender");
            System.out.println("============+=================+=============+===================");

            while (resultSet.next()){
                int id = resultSet.getInt(1);
                String name = resultSet.getNString(2);
                int age = resultSet.getInt(3);
                String gender = resultSet.getNString(4);

                System.out.printf("|%-12s|%-20s|%-10s|%-12s|\n", id, name, age, gender );
                System.out.println("============+==================+=============+=================");
            }
        }catch (SQLException e){
            e.printStackTrace();
        }
    }

    public boolean getPatientById(int id){
        String query = "SELECT * FROM patients WHERE id = ?";
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
