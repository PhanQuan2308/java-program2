package session10.controller;

import session10.entity.Product;
import session10.model.ProductDAO;
import session10.model.ProductDAOImpl;

import java.sql.SQLException;
import java.util.ArrayList;
import java.util.Scanner;

public class ControllerProduct {
    private  static  final Scanner sc = new Scanner(System.in);

    public static void createProduct(){
        try {
            ProductDAOImpl productDAO = new ProductDAOImpl();

            System.out.println("Enter product id: ");
            int createProId = sc.nextInt();

            sc.nextLine(); // Đọc newline character

            System.out.println("Enter product name: ");
            String createProName = sc.nextLine();

            System.out.println("Enter description product: ");
            String createDesPro = sc.nextLine();

            System.out.println("Enter price product: ");
            double createPricePro = sc.nextDouble();

            Product product = new Product(createProId, createProName, createDesPro, createPricePro);
            productDAO.createProduct(product);
        } catch (SQLException e) {
            throw new RuntimeException(e);
        }
    }

    public  static void getProductById()  {
        try {
            System.out.println("Enter id Product to search: ");
            String getIdPro = sc.nextLine();
            ProductDAOImpl productDAO = new ProductDAOImpl();
            productDAO.getProductById(Integer.parseInt(getIdPro));
        } catch (Exception e) {
            throw new RuntimeException(e);
        }


    }

    public static void getAllProduct(){
        try {
            System.out.println("All products: ");
            ProductDAOImpl productDAO = new ProductDAOImpl();
            ArrayList<Product> allproducts = productDAO.getAllProduct();
            for(Product allproduct : allproducts){
                System.out.println("Product id: " + allproduct.getProduct_id());
                System.out.println("Product name: "+ allproduct.getProductName());
                System.out.println("Product description" + allproduct.getProductDesc());
                System.out.println("Product price: " + allproduct.getProductPrice());
                System.out.println("-----------------------------------------------");
            }

        } catch (Exception e) {
            throw new RuntimeException(e);
        }
    }

    public static void updateProduct() {
        try {
            System.out.println("Enter id product to update: ");
            int idProUp = sc.nextInt();

            sc.nextLine(); // Tiêu thụ newline character

            System.out.println("Enter new name product: ");
            String newNamePro = sc.nextLine();

            ProductDAOImpl productDAO = new ProductDAOImpl();

            Product product = new Product();
            product.setProduct_id(idProUp);
            product.setProductName(newNamePro);

            productDAO.updateProduct(product);
        } catch (Exception e) {
            throw new RuntimeException(e);
        }
    }


    public static void deleteProduct(){
        try {
            System.out.println("Enter id product to delete: ");
            int delIdPro = sc.nextInt();

            ProductDAOImpl productDAO = new ProductDAOImpl();
            boolean deleted = productDAO.deleteProduct(delIdPro);



        } catch (Exception e) {
            throw new RuntimeException(e);
        }
    }



}
