package com.ecommerce.product.exceptions;

/**
 * Handles and wraps exceptions that are caused by data insertion failures
 */
public class DataInsertionException extends RuntimeException{
    public DataInsertionException(String errorMessage, Exception ex){
        super(errorMessage, ex);
    }
}
