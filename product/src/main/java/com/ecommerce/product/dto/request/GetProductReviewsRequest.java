package com.ecommerce.product.dto.request;

import javax.xml.bind.ValidationEvent;

public class GetProductReviewsRequest {

    private String productId;
    private Integer offset;
    private Integer limit;

    public GetProductReviewsRequest(String productId, Integer offset, Integer limit) {
        this.productId = productId;
        this.offset = offset;
        this.limit = limit;
    }

    public String getProductId() {
        return productId;
    }

    public Integer getOffset() {
        return offset;
    }

    public Integer getLimit() {
        return limit;
    }
}
