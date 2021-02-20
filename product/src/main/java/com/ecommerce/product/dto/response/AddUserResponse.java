package com.ecommerce.product.dto.response;

public class AddUserResponse {
    private String uderId;

    public AddUserResponse(String uderId){
        this.uderId = uderId;
    }

    public String getUderId() {
        return uderId;
    }

    public void setUderId(String uderId) {
        this.uderId = uderId;
    }
}
