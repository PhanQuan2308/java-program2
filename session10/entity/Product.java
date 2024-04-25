package session10.entity;

public class Product {
    private  int product_id;
    private double productPrice;
    private String productName, productDesc;

    public Product() {
    }

    public Product(int product_id, String productName, String productDesc, double productPrice) {
        this.product_id = product_id;
        this.productName = productName;
        this.productDesc = productDesc;
        this.productPrice = productPrice;
    }

    public int getProduct_id() {
        return product_id;
    }

    public void setProduct_id(int product_id) {
        this.product_id = product_id;
    }

    public String getProductName() {
        return productName;
    }

    public void setProductName(String productName) {
        this.productName = productName;
    }

    public String getProductDesc() {
        return productDesc;
    }

    public void setProductDesc(String productDesc) {
        this.productDesc = productDesc;
    }

    public double getProductPrice() {
        return productPrice;
    }

    public void setProductPrice(double productPrice) {
        this.productPrice = productPrice;
    }
}
