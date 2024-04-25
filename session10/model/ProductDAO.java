package session10.model;

import session10.entity.Product;

import java.util.ArrayList;

public interface ProductDAO {
    public void createProduct(Product product);

    public Product getProductById(int proId);

    public ArrayList<Product> getAllProduct();

    public void updateProduct(Product product);

    public boolean deleteProduct(int proId);

}
