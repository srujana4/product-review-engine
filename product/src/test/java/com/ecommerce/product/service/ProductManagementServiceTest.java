package com.ecommerce.product.service;

import com.ecommerce.product.TestUtil;
import com.ecommerce.product.dto.request.AddProductRequest;
import com.ecommerce.product.dto.response.AddProductResponse;
import com.ecommerce.product.dto.response.GetProductResponse;
import com.ecommerce.product.dto.response.GetProductReviewResponse;
import com.ecommerce.product.model.Product;
import com.ecommerce.product.model.ProductReview;
import com.ecommerce.product.repository.ProductRepository;
import com.ecommerce.product.repository.UserRepository;
import org.mockito.InjectMocks;
import org.mockito.Mockito;
import org.testng.Assert;
import org.testng.annotations.BeforeClass;
import org.testng.annotations.Test;

import java.util.List;

import static org.mockito.ArgumentMatchers.any;
import static org.mockito.Mockito.when;

@Test
public class ProductManagementServiceTest {
    private final ProductRepository productRepository = Mockito.mock(ProductRepository.class);
    private final UserRepository userRepository = Mockito.mock(UserRepository.class);

    private static String productId = "0462bc1c-ea2c-4140-8457-edc160391586";

    private List<Product> productList;
    private Product product;
    private Product product2;
    private AddProductRequest addProductRequest;

    private List<ProductReview> productReviewList;
    private ProductReview productReview;
    private ProductReview productReview2;

    @InjectMocks
    ProductManagementService productManagementService;

    @BeforeClass
    void setup(){
        product = TestUtil.getProduct();
        product2 = TestUtil.getProduct();
        productList.add(product);
        productList.add(product2);
        addProductRequest = TestUtil.getAddProductRequest();

        productReview = TestUtil.getProductReview();
        productReview2 = TestUtil.getProductReview();
        productReviewList.add(productReview);
        productReviewList.add(productReview2);
    }

    @Test
    public void getProductByIdTest(){
        when(productRepository.getProductById(any())).thenReturn(product);
        GetProductResponse response = productManagementService.getProductById(productId);

        Assert.assertNotNull(response);
        Assert.assertEquals(response.getId(), product.getId());
        Assert.assertEquals(response.getName(), product.getName());
        Assert.assertEquals(response.getDepartment(), product.getDepartment());
        Assert.assertEquals(response.getMaxPurchasableQuantity(), product.getMaxPurchasableQuantity());
        Assert.assertEquals(response.getPrice(), product.getPrice());
        Assert.assertEquals(response.getAvgRating(), product.getAvgRating());
    }

    @Test
    public void addProductTest(){

        Mockito.doNothing().when(productRepository).addProduct(product);
        AddProductResponse response = productManagementService.addNewProduct(addProductRequest);

        Assert.assertNotNull(response);
        Assert.assertNotNull(response.getProductId());
    }

    @Test
    public void getAllProductsTest(){
        when(productRepository.getAllProducts()).thenReturn(productList);
        List<GetProductResponse> responses = productManagementService.getAllProducts();

        Assert.assertNotNull(responses);
        Assert.assertNotEquals(responses.size(), 0);
    }

    @Test
    public void getAllReviewsOfProductTest(){
        when(productRepository.getProductById(any())).thenReturn(product2);

        List<GetProductReviewResponse> responses = productManagementService.getAllReviewsOfProduct(productId);

        Assert.assertNotNull(responses);
        Assert.assertNotEquals(responses.size(), 0);

    }

    @Test
    public void getAllReviewsOfProductByCriteriaTest(){
        when(productRepository.getProductById(any())).thenReturn(product2);
        when(productRepository.getReviewsOfProduct(any(), any(), any(), any())).thenReturn(productReviewList);

        List<GetProductReviewResponse> responses = productManagementService.getAllReviewsOfProduct(productId);

        Assert.assertNotNull(responses);
        Assert.assertNotEquals(responses.size(), 0);

    }

}
