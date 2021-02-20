package com.ecommerce.product.dto.request;

public class AddProductRequest {
    private String productName;
    private String productDescription;
    private int price;
    private int maxPurchasableQuantity;
    private int department;

    public String getProductName() {
        return productName;
    }

    public void setProductName(String productName) {
        this.productName = productName;
    }

    public String getProductDescription() {
        return productDescription;
    }

    public void setProductDescription(String productDescription) {
        this.productDescription = productDescription;
    }

    public int getPrice() {
        return price;
    }

    public void setPrice(int price) {
        this.price = price;
    }

    public int getMaxPurchasableQuantity() {
        return maxPurchasableQuantity;
    }

    public void setMaxPurchasableQuantity(int maxPurchasableQuantity) {
        this.maxPurchasableQuantity = maxPurchasableQuantity;
    }

    public int getDepartment() {
        return department;
    }

    public void setDepartment(int department) {
        this.department = department;
    }
}
