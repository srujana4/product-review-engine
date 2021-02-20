package com.ecommerce.product.model;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.FetchType;
import javax.persistence.Id;
import javax.persistence.OneToMany;
import javax.persistence.Table;
import java.util.ArrayList;
import java.util.List;
import java.util.Objects;

@Entity
@Table(name = "product")
public class Product implements java.io.Serializable{

    public static final String PRODUCT_TABLE_NAME = "product";

    @Id
    @Column(name = "id", unique = true, nullable = false)
    private String id;

    @Column(name = "name", unique = true, nullable = false)
    private String name;

    @Column(name = "description")
    private String description;

    @Column(name = "price", nullable = false)
    private int price;

    //TODO Ye kya hai
    @Column(name = "max_limit_for_user")
    private int maxPurchasableQuantity;

    @Column(name = "avg_rating", nullable = false)
    private float avgRating;

    @Column(name = "number_of_ratings", nullable = false)
    private int numberOfRatings;

    @Column(name = "is_active", nullable = false)
    private boolean isActive;

    @Column(name = "department")
    private int department;

    @Column(name = "created_at")
    private long createdAt;

    @OneToMany(fetch = FetchType.LAZY, mappedBy = PRODUCT_TABLE_NAME)
    private List<ProductReview> productReviews = new ArrayList<>(0);

    public Product() {
    }

    public String getId() {
        return id;
    }

    public void setId(String id) {
        this.id = id;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public String getDescription() {
        return description;
    }

    public void setDescription(String description) {
        this.description = description;
    }

    public int getPrice() {
        return price;
    }

    public void setPrice(int price) {
        this.price = price;
    }

    public int getMaxPurchasableQuantity() {
        return maxPurchasableQuantity;
    }

    public void setMaxPurchasableQuantity(int maxPurchasableQuantity) {
        this.maxPurchasableQuantity = maxPurchasableQuantity;
    }

    public float getAvgRating() {
        return avgRating;
    }

    public void setAvgRating(float avgRating) {
        this.avgRating = avgRating;
    }

    public int getNumberOfRatings() {
        return numberOfRatings;
    }

    public void setNumberOfRatings(int numberOfRatings) {
        this.numberOfRatings = numberOfRatings;
    }

    public boolean isActive() {
        return isActive;
    }

    public void setActive(boolean active) {
        isActive = active;
    }

    public long getCreatedAt() {
        return createdAt;
    }

    public void setCreatedAt(long createdAt) {
        this.createdAt = createdAt;
    }

    public int getDepartment() {
        return department;
    }

    public void setDepartment(int department) {
        this.department = department;
    }

    public List<ProductReview> getProductReviews() {
        return productReviews;
    }

    public void setProductReviews(List<ProductReview> productReviews) {
        this.productReviews = productReviews;
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) {
            return true;
        }
        if (!(o instanceof Product)) {
            return false;
        }
        Product product = (Product) o;
        return getId().equals(product.getId());
    }

    @Override
    public int hashCode() {
        return Objects.hash(getId());
    }
}


