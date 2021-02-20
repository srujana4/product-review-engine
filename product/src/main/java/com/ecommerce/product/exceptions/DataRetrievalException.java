package com.ecommerce.product.exceptions;

/**
 * Handles and wraps exceptions that are caused by data retrieval failures
 */
public class DataRetrievalException extends RuntimeException{
    public DataRetrievalException(String errorMessage, Exception ex){
        super(errorMessage, ex);
    }
}
