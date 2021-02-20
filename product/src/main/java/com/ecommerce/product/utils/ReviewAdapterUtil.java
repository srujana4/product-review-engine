package com.ecommerce.product.utils;

import com.ecommerce.product.dto.response.GetProductReviewResponse;
import com.ecommerce.product.model.ProductReview;

import java.util.ArrayList;
import java.util.List;

public class ReviewAdapterUtil {
    public static List<GetProductReviewResponse> getProductReviewResponses(List<ProductReview> productReviews){
        List<GetProductReviewResponse> productReviewResponseList = new ArrayList<>();
        productReviews.forEach(review -> {
            GetProductReviewResponse productReviewResponse = new GetProductReviewResponse(review.getId(),
                    review.getUser().getId(), review.getUser().getName(), review.getProduct().getId(),
                    review.getProduct().getName(), review.getRating(), review.getReview(), review.getTimeStamp());
            productReviewResponseList.add(productReviewResponse);
        });
        return productReviewResponseList;
    }
}
