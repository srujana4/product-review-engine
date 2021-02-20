package com.ecommerce.product.service;

import com.ecommerce.product.dto.request.AddProductRequest;
import com.ecommerce.product.dto.request.AddReviewRequest;
import com.ecommerce.product.dto.response.AddProductResponse;
import com.ecommerce.product.dto.response.AddReviewResponse;
import com.ecommerce.product.dto.response.GetProductResponse;
import com.ecommerce.product.dto.response.GetProductReviewResponse;

import java.util.List;

public interface ProductManagementService {

    GetProductResponse getProductByProductId(String productId);

    AddProductResponse addNewProduct(AddProductRequest addProductRequest);

    AddReviewResponse addNewReview(AddReviewRequest addReviewRequest);

    List<GetProductResponse> getAllProducts();

    List<GetProductReviewResponse> getAllReviewsOfProduct(String productId);

    List<GetProductReviewResponse> getAllReviewsOfProductByCriteria(String productId, Integer offset, Integer limit);
}
