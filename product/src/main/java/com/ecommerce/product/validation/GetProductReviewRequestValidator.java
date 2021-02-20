package com.ecommerce.product.validation;

import com.ecommerce.product.dto.request.GetProductReviewsRequest;
import com.ecommerce.product.exceptions.BadRequestException;
import org.springframework.stereotype.Component;

import java.util.ArrayList;
import java.util.List;

@Component
public class GetProductReviewRequestValidator implements RequestValidator<GetProductReviewsRequest> {

    @Override
    public void validate(GetProductReviewsRequest getProductReviewsRequest) {
        List<String> validationFailures = new ArrayList<>();

        if (getProductReviewsRequest == null) {
            validationFailures.add("request should not be null");
        } else {
            String productId = getProductReviewsRequest.getProductId();
            if (productId == null) {
                validationFailures.add("product Id should not be empty");
            }

            Integer limit = getProductReviewsRequest.getLimit();
            if (limit != null && limit < 0) {
                validationFailures.add("limit should not be negative");
            }

            Integer offset = getProductReviewsRequest.getOffset();
            if (offset != null && offset < 0) {
                validationFailures.add("offset should not be negative");
            }
        }

        if (!validationFailures.isEmpty()) {
            throw new BadRequestException(validationFailures.toString());
        }
    }

}
