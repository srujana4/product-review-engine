package com.ecommerce.product.controller;

import com.ecommerce.product.dto.request.AddProductRequest;
import com.ecommerce.product.dto.request.AddReviewRequest;
import com.ecommerce.product.dto.response.AddProductResponse;
import com.ecommerce.product.dto.response.AddReviewResponse;
import com.ecommerce.product.dto.response.GetProductResponse;
import com.ecommerce.product.dto.response.GetProductReviewResponse;
import com.ecommerce.product.service.ProductManagementService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;
import org.springframework.http.HttpStatus;

import java.util.List;


@RestController
@RequestMapping(value = "/products")
public class ProductController {

    @Autowired
    private ProductManagementService productManagementService;

    @PostMapping(value = "", produces = "application/json")
    public ResponseEntity<AddProductResponse> createProduct(@RequestBody AddProductRequest addProductRequest) {
        return new ResponseEntity<>( productManagementService.addNewProduct(addProductRequest), HttpStatus.CREATED );
    }

    @GetMapping(value="/{productId}", produces = "application/json")
    public ResponseEntity<GetProductResponse> getProductById(@PathVariable("productId") String productId){

        GetProductResponse response = productManagementService.getProductByProductId(productId);
        return new ResponseEntity<>(response, HttpStatus.OK);
    }

    @GetMapping(value="", produces = "application/json")
    public ResponseEntity<List<GetProductResponse>> getAllProducts(){

        List<GetProductResponse> response = productManagementService.getAllProducts();
        return new ResponseEntity<>(response, HttpStatus.OK);
    }

    @PostMapping(value = "/reviews", produces = "application/json")
    public ResponseEntity<AddReviewResponse> addReview(@RequestBody AddReviewRequest addReviewRequest) {
        return new ResponseEntity<>( productManagementService.addNewReview(addReviewRequest), HttpStatus.CREATED );
    }

    @GetMapping(value="/{productId}/reviews", produces = "application/json")
    public ResponseEntity<List<GetProductReviewResponse>> getAllReviewsOfProduct(
            @PathVariable("productId") String productId, @RequestParam(value = "offset", required = false) Integer offset,
            @RequestParam(value = "limit", required = false) Integer limit){

        List<GetProductReviewResponse> productReviewResponseList =
                productManagementService.getAllReviewsOfProductByCriteria(productId, offset, limit);
        return new ResponseEntity<>(productReviewResponseList, HttpStatus.OK);
    }
}

