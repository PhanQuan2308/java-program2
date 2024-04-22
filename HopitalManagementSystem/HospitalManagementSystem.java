package HopitalManagementSystem;

import java.sql.*;
import java.util.Scanner;

public class HospitalManagementSystem {
    private static final String url = "jdbc:mysql://localhost:3306/hopital";
    private static final String username = "root";
    private static final String password = "";

    public static void bookAppointment(Patient patient, Doctor doctor, Connection connection, Scanner scanner) {
        System.out.println("Enter Patient Id: ");
        int patientId = scanner.nextInt();
        System.out.println("Enter Doctor Id: ");
        int doctorId = scanner.nextInt();
        System.out.println("Enter appointment date (YYYY-MM-DD): ");
        String appointmentDate = scanner.next();
        if (patient.getPatientById(patientId) && doctor.getDoctorById(doctorId)) {
            if (checkDoctorAvailability(doctorId, appointmentDate, connection)) {
                String appointmentQuery = "INSERT INTO appointments (patient_id, doctor_id, appointment_date) VALUES (?, ?, ?)";
                try (PreparedStatement preparedStatement = connection.prepareStatement(appointmentQuery)) {
                    preparedStatement.setInt(1, patientId);
                    preparedStatement.setInt(2, doctorId);
                    preparedStatement.setString(3, appointmentDate);
                    int rowsAffected = preparedStatement.executeUpdate();
                    if (rowsAffected > 0) {
                        System.out.println("Appointment Booked!");
                    } else {
                        System.out.println("Failed to Book Appointment!");
                    }
                } catch (SQLException e) {
                    handleSQLException(e);
                }
            }
        } else {
            System.out.println("Either Doctor or Patient does not exist!");
        }
    }

    public static void main(String[] args) {
        try {
            Class.forName("com.mysql.cj.jdbc.Driver");
        } catch (ClassNotFoundException e) {
            e.printStackTrace();
            return;
        }

        try (Connection connection = DriverManager.getConnection(url, username, password);
             Scanner scanner = new Scanner(System.in)) {
            Patient patient = new Patient(connection, scanner);
            Doctor doctor = new Doctor();
            while (true) {
                System.out.println("HOSPITAL MANAGEMENT SYSTEM ");
                System.out.println("1. Add Patient");
                System.out.println("2. View Patients");
                System.out.println("3. View Doctors");
                System.out.println("4. Book Appointment");
                System.out.println("5. Exit");
                System.out.println("Enter your choice: ");
                int choice = scanner.nextInt();

                switch (choice) {
                    case 1:
                        patient.addPatient();
                        break;
                    case 2:
                        patient.viewPatients();
                        break;
                    case 3:
                        doctor.viewDoctors();
                        break;
                    case 4:
                        bookAppointment(patient, doctor, connection, scanner);
                        break;
                    case 5:
                        return;
                    default:
                        System.out.println("Invalid input");
                }
            }
        } catch (SQLException e) {
            handleSQLException(e);
        }
    }

    public static boolean checkDoctorAvailability(int doctorId, String appointmentDate, Connection connection) {
        String query = "SELECT COUNT(*) AS count FROM appointments WHERE doctor_id = ? AND appointment_date = ?";
        try (PreparedStatement preparedStatement = connection.prepareStatement(query)) {
            preparedStatement.setInt(1, doctorId);
            preparedStatement.setString(2, appointmentDate);
            try (ResultSet resultSet = preparedStatement.executeQuery()) {
                if (resultSet.next()) {
                    int count = resultSet.getInt("count");
                    return count > 0;
                }
            }
        } catch (SQLException e) {
            handleSQLException(e);
        }
        return false;
    }

    private static void handleSQLException(SQLException e) {
        System.out.println("SQL Exception occurred: " + e.getMessage());
        // Log the exception or perform appropriate error handling.
    }
}
