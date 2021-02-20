package com.ecommerce.product.service;

import com.ecommerce.product.dto.request.AddUserRequest;
import com.ecommerce.product.dto.response.AddUserResponse;
import com.ecommerce.product.dto.response.GetProductReviewResponse;
import com.ecommerce.product.dto.response.GetUserResponse;

import java.util.List;

public interface UserManagementService {

    AddUserResponse addNewUser(AddUserRequest addUserRequest);

    List<GetUserResponse> getAllUsers();

    GetUserResponse getUserById(String userId);

    List<GetProductReviewResponse> getAllReviewsOfUser(String userId);

    List<GetProductReviewResponse> getAllReviewsOfUserByCriteria(String userId, Integer offset, Integer limit);

}
