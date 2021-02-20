package com.ecommerce.product.repository;

import com.ecommerce.product.dto.response.AddProductResponse;
import com.ecommerce.product.model.User;

import java.util.List;

public interface UserRepository {

    void addUser(User user);

    List<User> getAllUsers();

    User getUserById(String userId);

}
