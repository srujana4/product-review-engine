package com.ecommerce.product.service;

import com.ecommerce.product.dto.request.AddProductRequest;
import com.ecommerce.product.dto.request.AddProductReviewRequest;
import com.ecommerce.product.dto.response.AddProductResponse;
import com.ecommerce.product.dto.response.AddReviewResponse;
import com.ecommerce.product.dto.response.GetProductResponse;
import com.ecommerce.product.dto.response.GetProductReviewResponse;
import com.ecommerce.product.exceptions.DataInsertionException;
import com.ecommerce.product.exceptions.DataRetrievalException;
import com.ecommerce.product.exceptions.ProductManagementRuntimeException;
import com.ecommerce.product.exceptions.ResourceNotFoundException;
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

/**
 * Service to handle all the operations on Product cataloge. This includes adding a product, Getting product ,
 * Adding review etc.
 */
@Service
public class ProductManagementServiceImpl implements ProductManagementService{

    @Autowired
    private ProductRepository productRepository;

    @Autowired
    private UserRepository userRepository;

    /**
     * Returns product information by Id.
     * @param productId
     * @return
     */
    @Override
    public GetProductResponse getProductById(String productId) {
        Product dbProduct;

        try {
            dbProduct = productRepository.getProductById(productId);
        }catch(DataRetrievalException ex){
            throw new ProductManagementRuntimeException(ex);
        }
        if(dbProduct == null){
            throw new ResourceNotFoundException(String.format("Product with Id: {} is not found", productId));
        }

        return ProductAdapterUtil.convertToProductResponse(dbProduct);
    }

    /**
     * Adds a new product into the catalog.
     * @param addProductRequest
     * @return
     */
    @Override
    public AddProductResponse addNewProduct(AddProductRequest addProductRequest) {
        Product product = ProductAdapterUtil.convertToProductModel(addProductRequest);
        try {
            productRepository.addProduct(product);
        } catch (DataInsertionException ex) {
            throw new ProductManagementRuntimeException(ex);
        }

        return new AddProductResponse(product.getId());
    }

    /**
     * Adds a new review against a product.
     * @param addProductReviewRequest
     * @return
     */
    @Override
    public AddReviewResponse addNewReview(AddProductReviewRequest addProductReviewRequest) {
        Product product = productRepository.getProductById(addProductReviewRequest.getProductId());
        User user = userRepository.getUserById(addProductReviewRequest.getUserId());
        ProductReview review = ProductAdapterUtil.convertToProductReviewModel(addProductReviewRequest, product, user);

        try {
            productRepository.addReviewForProduct(review);
        } catch (DataInsertionException ex) {
            throw new ProductManagementRuntimeException(ex);
        }

        return new AddReviewResponse(review.getId());
    }

    /**
     * Returns the product catalog.
     * @return
     */
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

    /**
     * Returns all the reviews given against a product.
     * @param productId
     * @return
     */
    @Override
    public List<GetProductReviewResponse> getAllReviewsOfProduct(String productId) {
        Product product = productRepository.getProductById(productId);
        if (product == null) {
            throw new ResourceNotFoundException(String.format("Product with Id: {} is not found in the DB", productId));
        }

        List<ProductReview> productReviews = product.getProductReviews();

        return ReviewAdapterUtil.getProductReviewResponses(productReviews);
    }

    /**
     * Returns all the reviews against a product in a paginated manner.
     * @param productId
     * @param offset
     * @param limit
     * @return
     */
    @Override
    public List<GetProductReviewResponse> getAllReviewsOfProductByCriteria(String productId, Integer offset,
                                                                           Integer limit) {
        List<ProductReview> productReviews;
        Product product = productRepository.getProductById(productId);
        if (product == null) {
            throw new ResourceNotFoundException(String.format("Product with Id: {} is not found in the DB", productId));
        }

        try {
            productReviews = productRepository.getReviewsOfProduct(null, product, offset, limit);
        } catch (DataRetrievalException ex) {
            throw new ProductManagementRuntimeException(ex);
        }

        return ReviewAdapterUtil.getProductReviewResponses(productReviews);
    }

}
