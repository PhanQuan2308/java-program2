package session10.controller;

import session10.entity.Order;
import session10.model.OrderDAOImpl;

import java.sql.Array;
import java.sql.Date;
import java.sql.SQLException;
import java.time.LocalDate;
import java.util.ArrayList;
import java.util.Scanner;

public class ControllerOrder {
    private  static final Scanner sc = new Scanner(System.in);

    public static void creatOrder() {
        try {
            System.out.println("Add order id: ");
            int addOrId = sc.nextInt();

            System.out.println("Add customer id: ");
            int addCusId = sc.nextInt();

            System.out.println("Add date order (YYYY-MM-DD): ");
            String addDateOrderStr = sc.next();

            // Chuyển đổi chuỗi thành java.sql.Date
            java.sql.Date addDateOrderSql = java.sql.Date.valueOf(addDateOrderStr);

            // Tạo đối tượng Order từ dữ liệu nhập vào
            Order newOrder = new Order(addOrId, addCusId, addDateOrderSql);

            // Gọi phương thức createOrder để thêm đơn hàng vào cơ sở dữ liệu
            OrderDAOImpl orderDAO = new OrderDAOImpl();
            orderDAO.createOrder(newOrder);

            System.out.println("Order added successfully!");

        } catch (Exception e) {
            // In ra thông điệp lỗi chi tiết
            System.out.println("Error occurred: " + e.getMessage());
            // In ra stack trace của ngoại lệ
            e.printStackTrace();
        }
    }
    public  static  void getOrderById() {
        try {
            System.out.println("Enter order id to search: ");
            int idSearchOrder = sc.nextInt();
            OrderDAOImpl orderDAO = new OrderDAOImpl();
            orderDAO.getOrderById(idSearchOrder);
        } catch (Exception e) {
            throw new RuntimeException(e);
        }


    }
    public static void getAllOrder(){
        try {
            System.out.println("All orders: ");
            OrderDAOImpl orderDAO = new OrderDAOImpl();
            ArrayList<Order> orders = orderDAO.getAllOrder();

            for (Order order : orders){
                System.out.println("Order id: " + order.getOrder_id());
                System.out.println("Customer id: " + order.getCustomer_id());
                System.out.println("Order date: " + order.getOrder_date());
                System.out.println("====================================");
        }


        } catch (SQLException e) {
            throw new RuntimeException(e);
        }


    }
    public  static void updateOrder(){
        try {
            System.out.println("Enter order id to update: ");
            int idUpOrder = sc.nextInt();

            System.out.println("All information of order id: ");
            OrderDAOImpl orderDAO = new OrderDAOImpl();
            Order existingOrder = orderDAO.getOrderById(idUpOrder);
            if(existingOrder != null){
                System.out.println("Enter new customer id: ");
                int newIdCus = sc.nextInt();
                sc.nextLine();

                System.out.println("Enter new order date(yyyy-mm-dd): ");

                String newOrderDateStr = sc.nextLine();
                LocalDate newOrderDate   = LocalDate.parse(newOrderDateStr);


                existingOrder.setCustomer_id(newIdCus);
                existingOrder.setOrder_date(Date.valueOf(newOrderDate));
                orderDAO.updateOrder(existingOrder);

            }else {
                System.out.println("Order not found");
            }





        } catch (Exception e) {
            throw new RuntimeException(e);
        }
    }

    public  static void deleteOrder(){
        try {
            System.out.println("Enter id order to delete");
            int idDeleOrder = sc.nextInt();
            sc.nextLine();
            OrderDAOImpl orderDAO  = new OrderDAOImpl();
            orderDAO.deleteOrder(idDeleOrder);
        } catch (Exception e) {
            throw new RuntimeException(e);
        }
    }

    public static void main(String[] args) {
       getAllOrder();
    }

}
