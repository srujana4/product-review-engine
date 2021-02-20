package com.ecommerce.product.dto.response;

public class GetProductReviewResponse {
    private String id;
    private String userId;
    private String productId;
    private int rating;
    private String review;
    private long timeStamp;

    public GetProductReviewResponse() {
    }

    public GetProductReviewResponse(String id, String userId, String productId, int rating, String review, long timeStamp) {
        this.id = id;
        this.userId = userId;
        this.productId = productId;
        this.rating = rating;
        this.review = review;
        this.timeStamp = timeStamp;
    }

    public String getId() {
        return id;
    }

    public void setId(String id) {
        this.id = id;
    }

    public String getUserId() {
        return userId;
    }

    public void setUserId(String userId) {
        this.userId = userId;
    }

    public String getProductId() {
        return productId;
    }

    public void setProductId(String productId) {
        this.productId = productId;
    }

    public int getRating() {
        return rating;
    }

    public void setRating(int rating) {
        this.rating = rating;
    }

    public String getReview() {
        return review;
    }

    public void setReview(String review) {
        this.review = review;
    }

    public long getTimeStamp() {
        return timeStamp;
    }

    public void setTimeStamp(long timeStamp) {
        this.timeStamp = timeStamp;
    }
}
