package com.ecommerce.product.utils;

import com.ecommerce.product.dto.request.AddProductRequest;
import com.ecommerce.product.dto.request.AddProductReviewRequest;
import com.ecommerce.product.dto.response.GetProductResponse;
import com.ecommerce.product.model.Product;
import com.ecommerce.product.model.ProductReview;
import com.ecommerce.product.model.User;

import java.time.Instant;
import java.util.UUID;

public class ProductAdapterUtil {
    public static GetProductResponse convertToProductResponse(Product product){
        if(product != null){
            GetProductResponse response = new GetProductResponse();
            response.setId(product.getId());
            response.setActive(product.isActive());
            response.setAvgRating(product.getAvgRating());
            response.setDepartment(product.getDepartment());
            response.setDescription(product.getDescription());
            response.setMaxPurchasableQuantity(product.getMaxPurchasableQuantity());
            response.setNumberOfRatings(product.getNumberOfRatings());
            response.setName(product.getName());
            response.setPrice(product.getPrice());

            return response;
        }
        return null;
    }

    public static Product convertToProductModel(AddProductRequest addProductRequest){
        Product product = new Product();
        product.setId(UUID.randomUUID().toString());
        product.setCreatedAt(Instant.now().toEpochMilli());
        product.setActive(true);
        product.setName(addProductRequest.getProductName());
        product.setDescription(addProductRequest.getProductDescription());
        product.setDepartment(addProductRequest.getDepartment());
        product.setMaxPurchasableQuantity(addProductRequest.getMaxPurchasableQuantity());
        product.setPrice(addProductRequest.getPrice());

        return product;
    }

    public static ProductReview convertToProductReviewModel(AddProductReviewRequest addProductReviewRequest, Product product, User user){
        ProductReview productReview = new ProductReview();
        productReview.setId(UUID.randomUUID().toString());
        productReview.setTimeStamp(Instant.now().toEpochMilli());
        productReview.setProduct(product);
        productReview.setUser(user);
        productReview.setRating(addProductReviewRequest.getRating());
        productReview.setReview(addProductReviewRequest.getReview());

        return productReview;
    }
}
