package com.ecommerce.product;

import com.ecommerce.product.dto.request.AddProductRequest;
import com.ecommerce.product.model.Product;
import com.ecommerce.product.model.ProductReview;
import com.ecommerce.product.model.User;

import java.time.Instant;
import java.util.ArrayList;
import java.util.List;
import java.util.UUID;

public class TestUtil {
    public static Product getProduct(){
        Product product = new Product();
        product.setDepartment(1);
        product.setNumberOfRatings(2);
        product.setAvgRating(4);
        product.setCreatedAt(Instant.now().toEpochMilli());
        product.setPrice(500);
        product.setMaxPurchasableQuantity(10);
        product.setDescription("Product Description");
        product.setName("ProductName");
        product.setActive(true);
        product.setId(UUID.randomUUID().toString());

        return product;
    }

    public static AddProductRequest getAddProductRequest(){
        AddProductRequest request = new AddProductRequest();
        request.setDepartment(1);
        request.setPrice(13);
        request.setProductDescription("Description");
        request.setMaxPurchasableQuantity(10);
        request.setProductName("ProductName");

        return request;
    }

    public static User getUser(){
        User user = new User();
        user.setId(UUID.randomUUID().toString());
        user.setName("User");
        user.setEmail("user@gmail.com");
        user.setContact("1234567890");
        user.setAddress("Address");
        user.setJoinedOn(Instant.now().toEpochMilli());

        return user;
    }

    public static ProductReview getProductReview(){
        ProductReview productReview = new ProductReview();
        productReview.setReview("Good");
        productReview.setRating(5);
        productReview.setProduct(getProduct());
        productReview.setUser(getUser());

        return productReview;
    }
}
