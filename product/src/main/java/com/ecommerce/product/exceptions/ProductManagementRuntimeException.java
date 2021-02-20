package com.ecommerce.product.exceptions;

public class ProductManagementRuntimeException extends RuntimeException{
    public ProductManagementRuntimeException(DataInsertionException exception){
        super(exception);
    }
}
