package com.ecommerce.product.dto.response;

public class GetProductResponse {
    private String id;
    private String name;
    private String description;
    private int price;
    private int maxPurchasableQuantity;
    private float avgRating;
    private int numberOfRatings;
    private boolean isActive;
    private int department;

    public GetProductResponse(){

    }

    public GetProductResponse(String id, String name, String description, int price, int maxPurchasableQuantity,
                              float avgRating, int numberOfRatings, boolean isActive, int department) {
        this.id = id;
        this.name = name;
        this.description = description;
        this.price = price;
        this.maxPurchasableQuantity = maxPurchasableQuantity;
        this.avgRating = avgRating;
        this.numberOfRatings = numberOfRatings;
        this.isActive = isActive;
        this.department = department;
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

    public int getDepartment() {
        return department;
    }

    public void setDepartment(int department) {
        this.department = department;
    }
}
