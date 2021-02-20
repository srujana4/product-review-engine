package com.ecommerce.product.model;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.FetchType;
import javax.persistence.Id;
import javax.persistence.JoinColumn;
import javax.persistence.ManyToOne;
import javax.persistence.Table;
import java.util.Objects;

@Entity
@Table(name = "product_review")
public class ProductReview {
    public static class ProductReviewProperties{
        public static final String PRODUCT_ID = "product";
        public static final String USER_ID = "user";
    }

    public static final String PRODUCT_REVIEW_TABLE_NAME = "product_review";

    @Id
    @Column(name = "id", unique = true, nullable = false)
    private String id;

    @Column(name = "rating", nullable = false)
    private int rating;

    @Column(name = "review", nullable = false)
    private String review;

    @Column(name = "timeStamp", nullable = false)
    private long timeStamp;

    @ManyToOne(fetch = FetchType.LAZY)
    @JoinColumn(name = "fkproduct", nullable = false)
    private Product product;

    @ManyToOne(fetch = FetchType.LAZY)
    @JoinColumn(name = "fkuser", nullable = false)
    private User user;

    public String getId() {
        return id;
    }

    public void setId(String id) {
        this.id = id;
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

    public Product getProduct() {
        return product;
    }

    public void setProduct(Product product) {
        this.product = product;
    }

    public User getUser() {
        return user;
    }

    public void setUser(User user) {
        this.user = user;
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) {
            return true;
        }
        if (!(o instanceof ProductReview)) {
            return false;
        }
        ProductReview that = (ProductReview) o;
        return getId().equals(that.getId());
    }

    @Override
    public int hashCode() {
        return Objects.hash(getId());
    }
}
