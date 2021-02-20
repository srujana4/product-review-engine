package com.ecommerce.product.validation;

import com.ecommerce.product.dto.request.AddProductReviewRequest;
import com.ecommerce.product.dto.request.GetProductReviewsRequest;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

@Component
public class RequestValidationFactory {

    @Autowired
    private RequestValidator addProductReviewRequestValidator;

    @Autowired
    private RequestValidator getProductReviewRequestValidator;

    /**
     * Returns appropriate validator based on the request type.
     *
     * @param object
     * @return
     */
    public RequestValidator getValidator(Object object) {
        if (object instanceof AddProductReviewRequest) {
            return addProductReviewRequestValidator;
        } else if (object instanceof GetProductReviewsRequest) {
            return getProductReviewRequestValidator;
        }

        return null;
    }
}
