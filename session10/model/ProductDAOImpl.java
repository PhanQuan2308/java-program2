package session10.model;

import session10.entity.Product;
import session9.DBConnection;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.function.Predicate;

public class ProductDAOImpl implements ProductDAO{
    private final Connection conn = DBConnection.getConnection();
    private final String SQL_CREATE_PRODUCT = "insert into products values(?,?,?,?)";
    private final String SQL_GET_PRODUCT_BY_ID = "select * from products where product_id = ?";
    private final String SQL_GET_ALL_PRODUCT = "select * from products";
    private final String UPDATE_PRODUCT = "update products set product_name = ? where product_id = ?";
    private final String SQL_DELETE_PRODUCT = "delete from products where  product_id  =?";

    public ProductDAOImpl() throws SQLException {
    }

    @Override
    public void createProduct(Product product) {
        try {
            PreparedStatement preparedStatement = conn.prepareStatement(SQL_CREATE_PRODUCT);
            preparedStatement.setInt(1, product.getProduct_id());
            preparedStatement.setString(2, product.getProductName());
            preparedStatement.setString(3, product.getProductDesc());
            preparedStatement.setDouble(4, product.getProductPrice());
            int rs = preparedStatement.executeUpdate();
            if (rs > 0) {
                System.out.println("Create success!");
            } else {
                System.out.println("Create fail");
            }

        } catch (SQLException e) {
            e.printStackTrace();
        }
    }

    @Override
    public Product getProductById(int proId) {
        Product product = null;
        try {
             product = new Product();
            PreparedStatement preparedStatement = conn.prepareStatement(SQL_GET_PRODUCT_BY_ID);
            preparedStatement.setInt(1,proId);
            ResultSet rs = preparedStatement.executeQuery();
            while (rs.next()){
                product.setProduct_id(rs.getInt("product_id"));
                product.setProductName(rs.getString("product_name"));
                product.setProductDesc(rs.getString("description"));
                product.setProductPrice(rs.getDouble("price"));

                System.out.println("Product ID  is: " + product.getProduct_id());
                System.out.println("Product name is: " + product.getProductName());
                System.out.println("Description is: " + product.getProductDesc());
                System.out.println("Price is: " + product.getProductPrice());


            }
        }catch (SQLException e){
            e.printStackTrace();
        }
    return product;

        
    }

    @Override
    public ArrayList<Product> getAllProduct() {
        ArrayList<Product> allProducts = null;
        try {
            allProducts = new ArrayList<>();
            PreparedStatement preparedStatement = conn.prepareStatement(SQL_GET_ALL_PRODUCT);
            ResultSet rs = preparedStatement.executeQuery();
            if(false){
                while (rs.next()) {
                Product product = new Product();
                product.setProduct_id(rs.getInt("product_id"));
                product.setProductName(rs.getString("product_name"));
                product.setProductDesc(rs.getString("description"));
                product.setProductPrice(rs.getDouble("price"));
                allProducts.add(product);
                }
            }else {
            System.out.println("No product in data base");
        }

        } catch (SQLException e) {
            e.printStackTrace();
        }


        return allProducts;
    }


    @Override
    public void updateProduct(Product product) {
        try {
            PreparedStatement preparedStatement = conn.prepareStatement(UPDATE_PRODUCT);
            preparedStatement.setString(1, product.getProductName());
            preparedStatement.setInt(2, product.getProduct_id());
            int rs = preparedStatement.executeUpdate();
            if (rs > 0) {
                System.out.println("Update success");
            } else {
                System.out.println("Update fail");
            }
        } catch (Exception e) {
            throw new RuntimeException(e);
        }
    }



    @Override
    public boolean deleteProduct(int proId) {
        boolean deleted = false;
        try {
            PreparedStatement preparedStatement = conn.prepareStatement(SQL_DELETE_PRODUCT);
            preparedStatement.setString(1, String.valueOf(proId));
            if(preparedStatement.executeUpdate() > 0 ){
                System.out.println("Deleted success");
                deleted = true;
            }else {
                System.out.println("Delete fail");
            }
        } catch (Exception e) {
            throw new RuntimeException(e);
        }
        return deleted;
    }
}
