package com.ecommerce.product.validation;

public interface RequestValidator<T> {

    void validate(T t);
}
