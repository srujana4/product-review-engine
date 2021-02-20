package com.ecommerce.product.service;

import com.ecommerce.product.dto.request.AddProductRequest;
import com.ecommerce.product.dto.request.AddReviewRequest;
import com.ecommerce.product.dto.response.AddProductResponse;
import com.ecommerce.product.dto.response.AddReviewResponse;
import com.ecommerce.product.dto.response.GetProductResponse;
import com.ecommerce.product.dto.response.GetProductReviewResponse;
import com.ecommerce.product.exceptions.DataInsertionException;
import com.ecommerce.product.exceptions.ProductManagementRuntimeException;
import com.ecommerce.product.model.Product;
import com.ecommerce.product.model.ProductReview;
import com.ecommerce.product.model.User;
import com.ecommerce.product.repository.ProductRepository;
import com.ecommerce.product.repository.UserRepository;
import com.ecommerce.product.utils.ProductAdapterUtil;
import com.ecommerce.product.utils.ReviewAdapterUtil;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.ArrayList;
import java.util.List;

@Service
public class ProductManagementServiceImpl implements ProductManagementService{

    @Autowired
    private ProductRepository productRepository;

    @Autowired
    private UserRepository userRepository;

    @Override
    public GetProductResponse getProductByProductId(String productId) {
        Product dbProduct = productRepository.getProductById(productId);
        return ProductAdapterUtil.convertToProductResponse(dbProduct);
    }

    @Override
    public AddProductResponse addNewProduct(AddProductRequest addProductRequest) {
        Product product = ProductAdapterUtil.convertToProductModel(addProductRequest);
        productRepository.addProduct(product);

        return new AddProductResponse(product.getId());
    }

    @Override
    public AddReviewResponse addNewReview(AddReviewRequest addReviewRequest) {
        Product product = productRepository.getProductById(addReviewRequest.getProductId());
        User user = userRepository.getUserById(addReviewRequest.getUserId());
        ProductReview review = ProductAdapterUtil.convertToProductReviewModel(addReviewRequest, product, user);

        try{
            productRepository.addReviewForProduct(review);
        }
        catch (DataInsertionException ex){
            throw new ProductManagementRuntimeException(ex);
        }

        return new AddReviewResponse(review.getId());
    }

    @Override
    public List<GetProductResponse> getAllProducts() {
        List<Product> productList = productRepository.getAllProducts();
        List<GetProductResponse> productResponseList = new ArrayList<>();
        productList.forEach(product -> {
            GetProductResponse productResponse = new GetProductResponse(product.getId(), product.getName(),
                    product.getDescription(), product.getPrice(), product.getMaxPurchasableQuantity(), product.getAvgRating(),
                    product.getNumberOfRatings(), product.isActive(), product.getDepartment());
            productResponseList.add(productResponse);
        });
        return productResponseList;
    }

    @Override
    public List<GetProductReviewResponse> getAllReviewsOfProduct(String productId) {
        Product product = productRepository.getProductById(productId);
        List<ProductReview> productReviews = product.getProductReviews();

        return ReviewAdapterUtil.getProductReviewResponses(productReviews);
    }

    @Override
    public List<GetProductReviewResponse> getAllReviewsOfProductByCriteria(String productId, Integer offset, Integer limit) {
        Product product = productRepository.getProductById(productId);
        List<ProductReview> productReviews = productRepository.getReviewsOfProduct(null, product, offset, limit);

        return ReviewAdapterUtil.getProductReviewResponses(productReviews);
    }


}
