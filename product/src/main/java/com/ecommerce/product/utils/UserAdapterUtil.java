package com.ecommerce.product.utils;

import com.ecommerce.product.dto.request.AddUserRequest;
import com.ecommerce.product.dto.response.GetUserResponse;
import com.ecommerce.product.model.User;

import java.time.Instant;
import java.util.UUID;

public class UserAdapterUtil {
    public static GetUserResponse convertToUserResponse(User user){
        if(user != null){
            return new GetUserResponse(user.getId(), user.getName(), user.getJoinedOn(), user.getContact(), user.getEmail(), user.getAddress());
        }
        return null;
    }

    public static User convertToUserModel(AddUserRequest userRequest){
        User user = new User();
        user.setId(UUID.randomUUID().toString());
        user.setJoinedOn(Instant.now().toEpochMilli());
        user.setAddress(userRequest.getAddress());
        user.setContact(userRequest.getContact());
        user.setEmail(userRequest.getEmail());
        user.setName(userRequest.getUserName());

        return user;

    }
}
