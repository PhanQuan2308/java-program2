package sessionlogin;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;

public class ShoppingCart {
    private static final String SELECT_USER_CART_SQL = "SELECT cart_id, quantity FROM carts WHERE user_id = ?";

    public static void displayUserCart(int userId) {
        try {
            Connection conn = DbConnection.getConnection();
            PreparedStatement preparedStatement = conn.prepareStatement(SELECT_USER_CART_SQL);
            preparedStatement.setInt(1, userId);
            ResultSet rs = preparedStatement.executeQuery();

            while (rs.next()) {
                int cartId = rs.getInt("cart_id");
                int quantity = rs.getInt("quantity");
                // Hiển thị thông tin giỏ hàng, ví dụ:
                System.out.println("User ID: " + userId + ", Cart ID: " + cartId + ", Quantity: " + quantity);
            }

            conn.close();
        } catch (SQLException e) {
            e.printStackTrace();
        }
    }
}

