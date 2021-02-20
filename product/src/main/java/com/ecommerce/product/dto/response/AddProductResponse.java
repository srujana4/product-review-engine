package com.ecommerce.product.dto.response;

public class AddProductResponse {
    private String productId;

    public AddProductResponse(String productId) {
        this.productId = productId;
    }

    public String getProductId() {
        return productId;
    }

    public void setProductId(String productId) {
        this.productId = productId;
    }
}
