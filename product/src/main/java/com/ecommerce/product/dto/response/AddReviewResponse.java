package com.ecommerce.product.dto.response;

public class AddReviewResponse {
    private String reviewId;

    public AddReviewResponse(String reviewId) {
        this.reviewId = reviewId;
    }

    public String getReviewId() {
        return reviewId;
    }

    public void setReviewId(String reviewId) {
        this.reviewId = reviewId;
    }
}
