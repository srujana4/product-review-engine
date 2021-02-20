package com.ecommerce.product.dto.response;

public class GetUserResponse {
    private String userId;
    private String userName;
    private long joinedOn;
    private String contact;
    private String email;

    public GetUserResponse(String userId, String userName, long joinedOn, String contact, String email, String address) {
        this.userId = userId;
        this.userName = userName;
        this.joinedOn = joinedOn;
        this.contact = contact;
        this.email = email;
        this.address = address;
    }

    public String getUserId() {
        return userId;
    }

    public void setUserId(String userId) {
        this.userId = userId;
    }

    public String getUserName() {
        return userName;
    }

    public void setUserName(String userName) {
        this.userName = userName;
    }

    public long getJoinedOn() {
        return joinedOn;
    }

    public void setJoinedOn(long joinedOn) {
        this.joinedOn = joinedOn;
    }

    public String getContact() {
        return contact;
    }

    public void setContact(String contact) {
        this.contact = contact;
    }

    public String getEmail() {
        return email;
    }

    public void setEmail(String email) {
        this.email = email;
    }

    public String getAddress() {
        return address;
    }

    public void setAddress(String address) {
        this.address = address;
    }

    private String address;
}
