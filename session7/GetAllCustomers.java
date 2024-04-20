package session7;

import java.sql.Connection;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;

public class GetAllCustomers {
    public static  void getAllCustomers() {
        Connection connection = null;
        Statement stm = null;
        ResultSet rs = null;

        try {
            // Gọi đối tượng connection
            connection = MySQLConnectDB.getMySqlConnection();

            // Tạo Statement (thi hành thao tác)
            stm = connection.createStatement();

            // Truy vấn lấy dữ liệu từ bảng customers
            String query = "SELECT * FROM customers";

            // Thực hiện truy vấn, trả kết quả từ cơ sở dữ liệu cho ResultSet
            rs = stm.executeQuery(query);

            // Đọc các bản ghi từ ResultSet
            while (rs.next()) {
                int cusId = rs.getInt(1);
                String fn = rs.getNString(2);
                String ln = rs.getNString(3);
                String email = rs.getNString(4);

                System.out.println("==============");
                System.out.println("Customer_ID: " + cusId);
                System.out.println("First Name: " + fn);
                System.out.println("Last Name: " + ln);
                System.out.println("Email: " + email);
            }
        } catch (SQLException e) {
            e.printStackTrace();
        } finally {
            try {
                // Đóng ResultSet, Statement và Connection sau khi sử dụng
                if (rs != null) {
                    rs.close();
                }
                if (stm != null) {
                    stm.close();
                }
                if (connection != null) {
                    connection.close();
                }
            } catch (SQLException e) {
                e.printStackTrace();
            }
        }
    }
}
