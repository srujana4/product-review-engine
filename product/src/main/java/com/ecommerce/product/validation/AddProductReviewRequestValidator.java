package com.ecommerce.product.validation;

import com.ecommerce.product.dto.request.AddProductReviewRequest;
import com.ecommerce.product.exceptions.BadRequestException;
import org.springframework.stereotype.Component;

import java.util.ArrayList;
import java.util.List;

@Component
public class AddProductReviewRequestValidator implements RequestValidator<AddProductReviewRequest> {

    @Override
    public void validate(AddProductReviewRequest addProductReviewRequest) {
        List<String> validationFailures = new ArrayList<>();

        if(addProductReviewRequest == null){
            validationFailures.add("request should not be null");
        } else {
            String productId = addProductReviewRequest.getProductId();
            if( productId == null) {
                validationFailures.add("product Id should not be empty");
            }

            String userId = addProductReviewRequest.getUserId();
            if(userId == null || userId.isEmpty()){
                validationFailures.add("user Id should not be empty");
            }

            Integer rating = addProductReviewRequest.getRating();
            if(rating == null || rating < 1 || rating > 5){
                validationFailures.add("rating should be between 1 and 5");
            }
        }

        if(!validationFailures.isEmpty()){
            throw new BadRequestException(validationFailures.toString());
        }
    }
}
