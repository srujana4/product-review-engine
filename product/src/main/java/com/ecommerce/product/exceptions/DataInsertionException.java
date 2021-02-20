package com.ecommerce.product.exceptions;

public class DataInsertionException extends RuntimeException{
    public DataInsertionException(String errorMessage, Exception ex){
        super(errorMessage, ex);
    }
}
