package com.ecommerce.product.exceptions;

/**
 * Handles and wraps the run time exceptions that are thrown in the Repository layer.
 */
public class ProductManagementRuntimeException extends RuntimeException {
    public ProductManagementRuntimeException(DataInsertionException exception) {
        super(exception);
    }

    public ProductManagementRuntimeException(DataRetrievalException exception) {
        super(exception);
    }
}
