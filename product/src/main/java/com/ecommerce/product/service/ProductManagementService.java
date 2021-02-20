package com.ecommerce.product.service;

import com.ecommerce.product.dto.request.AddProductRequest;
import com.ecommerce.product.dto.request.AddProductReviewRequest;
import com.ecommerce.product.dto.response.AddProductResponse;
import com.ecommerce.product.dto.response.AddReviewResponse;
import com.ecommerce.product.dto.response.GetProductResponse;
import com.ecommerce.product.dto.response.GetProductReviewResponse;

import java.util.List;

public interface ProductManagementService {

    GetProductResponse getProductById(String productId);

    AddProductResponse addNewProduct(AddProductRequest addProductRequest);

    AddReviewResponse addNewReview(AddProductReviewRequest addProductReviewRequest);

    List<GetProductResponse> getAllProducts();

    List<GetProductReviewResponse> getAllReviewsOfProduct(String productId);

    List<GetProductReviewResponse> getAllReviewsOfProductByCriteria(String productId, Integer offset, Integer limit);
}
