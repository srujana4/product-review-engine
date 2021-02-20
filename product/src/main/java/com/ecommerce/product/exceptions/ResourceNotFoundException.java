package com.ecommerce.product.exceptions;

/**
 * Handles and wraps any kind of resource not found errors.
 */
public class ResourceNotFoundException extends RuntimeException {
    public ResourceNotFoundException(String message) {
        super(message);
    }
}
