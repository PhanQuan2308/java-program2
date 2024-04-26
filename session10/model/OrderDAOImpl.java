package session10.model;

import session10.entity.Order;
import session10.entity.Product;
import session9.DBConnection;

import java.sql.*;
import java.text.SimpleDateFormat;
import java.util.ArrayList;

public class OrderDAOImpl {
    private final Connection conn = DBConnection.getConnection();
    private static final String SQL_CREATE_ORDERS = "INSERT INTO orders values(?,?,?)";
    private static final String SQL_GET_ORDER_BY_ID = "SELECT * FROM orders WHERE order_id = ?";
    private static final String SQL_GET_ALL_ORDER = "SELECT * FROM orders";
    private static final String SQL_UPDATE_ORDER = "UPDATE orders set customer_id = ?, order_date = ? where order_id = ?";
    private static final String SQL_DELETE_ORDER = "DELETE from orders WHERE order_id = ?";

    public OrderDAOImpl() throws SQLException {
    }

    public void createOrder(Order order) {
        try {
            // Chuyển đổi từ java.util.Date sang java.sql.Date
            java.sql.Date orderDateSql = new java.sql.Date(order.getOrder_date().getTime());

            PreparedStatement preparedStatement = conn.prepareStatement(SQL_CREATE_ORDERS);
            preparedStatement.setInt(1, order.getOrder_id());
            preparedStatement.setInt(2, order.getCustomer_id());
            preparedStatement.setDate(3, orderDateSql);
            preparedStatement.executeUpdate();

            preparedStatement.close();

        } catch (Exception e) {
            throw new RuntimeException(e);
        }
    }


    public Order getOrderById(int intOrId){
        Order order = null;
        try {
            order = new Order();
            PreparedStatement preparedStatement = conn.prepareStatement(SQL_GET_ORDER_BY_ID);
            preparedStatement.setString(1, String.valueOf(intOrId));
            ResultSet rs = preparedStatement.executeQuery();
            while (rs.next()){
                order.setOrder_id(rs.getInt("order_id"));
                order.setCustomer_id(rs.getInt("customer_id"));
                order.setOrder_date(Date.valueOf(rs.getDate("order_date").toLocalDate()));

                System.out.println("Order Id: " + order.getOrder_id());
                System.out.println("Customer Id: "  + order.getCustomer_id());
                System.out.println("Date: " + order.getOrder_date());

            }


        } catch (Exception e) {
            throw new RuntimeException(e);
        }
        return order;
    }

    public ArrayList<Order> getAllOrder(){
        ArrayList<Order> allOrders = new ArrayList<>();

        try {
            PreparedStatement preparedStatement = conn.prepareStatement(SQL_GET_ALL_ORDER);
            ResultSet rs = preparedStatement.executeQuery();

            while (rs.next()){
                Order order = new Order();
                order.setOrder_id(rs.getInt("order_id"));
                order.setCustomer_id(rs.getInt("customer_id"));
                order.setOrder_date(Date.valueOf(rs.getDate("order_date").toLocalDate()));
                allOrders.add(order); // Add the order to the list
            }

            if(allOrders.isEmpty()){
                System.out.println("No order in database");
            }

        } catch (Exception e) {
            throw new RuntimeException(e);
        }
        return allOrders;
    }


    public void updateOrder(Order order){
        try {
            PreparedStatement preparedStatement = conn.prepareStatement(SQL_UPDATE_ORDER);
            preparedStatement.setInt(1, order.getCustomer_id());
            preparedStatement.setString(2, order.getOrder_date().toString());
            preparedStatement.setInt(3, order.getOrder_id()); // Truyền vào ID đơn hàng để cập nhật

            if(preparedStatement.executeUpdate() >0){
                System.out.println("Update success");
            }else {
                System.out.println("Update fail!");
            }
        } catch (Exception e) {
            throw new RuntimeException(e);
        }
    }
    public boolean  deleteOrder(int ordId){
        boolean deleted = false;
        try {
            PreparedStatement preparedStatement = conn.prepareStatement(SQL_DELETE_ORDER);
            preparedStatement.setInt(1,ordId);
            if(preparedStatement.executeUpdate() > 0 ){
                System.out.println("Deleted success!");
                deleted = true;
            }else {
                System.out.println("Delete fail!");
            }
        } catch (Exception e) {
            throw new RuntimeException(e);
        }
        return deleted;
    }
}
