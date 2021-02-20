package com.ecommerce.product.repository;

import com.ecommerce.product.model.Product;
import com.ecommerce.product.model.ProductReview;
import com.ecommerce.product.model.User;

import java.util.List;

public interface ProductRepository {

    void addProduct(Product product);

    List<Product> getAllProducts();

    Product getProductById(String productId);

    void addReviewForProduct(ProductReview productReview);

    List<ProductReview> getReviewsOfProduct(User user, Product product, Integer offset, Integer limit);



}
