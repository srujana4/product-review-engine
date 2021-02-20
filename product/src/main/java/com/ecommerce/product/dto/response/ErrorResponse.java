package com.ecommerce.product.dto.response;

/**
 * DTO to return the error message to the client. This is the default object used as response in error cases.
 */
public class ErrorResponse {

    private String errorMessage;

    public ErrorResponse(String errorMessage){
        this.errorMessage = errorMessage;
    }

    public String getErrorMessage() {
        return errorMessage;
    }

    public void setErrorMessage(String errorMessage) {
        this.errorMessage = errorMessage;
    }

}
