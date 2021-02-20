package com.ecommerce.product.service;

import com.ecommerce.product.dto.request.AddUserRequest;
import com.ecommerce.product.dto.response.AddUserResponse;
import com.ecommerce.product.dto.response.GetProductReviewResponse;
import com.ecommerce.product.dto.response.GetUserResponse;
import com.ecommerce.product.model.ProductReview;
import com.ecommerce.product.model.User;
import com.ecommerce.product.repository.ProductRepository;
import com.ecommerce.product.repository.UserRepository;
import com.ecommerce.product.utils.ReviewAdapterUtil;
import com.ecommerce.product.utils.UserAdapterUtil;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.ArrayList;
import java.util.List;

@Service
public class UserManagementServiceImpl implements UserManagementService {

    @Autowired
    private UserRepository userRepository;

    @Autowired
    private ProductRepository productRepository;

    @Override
    public AddUserResponse addNewUser(AddUserRequest addUserRequest) {
        User user = UserAdapterUtil.convertToUserModel(addUserRequest);
        userRepository.addUser(user);
        return new AddUserResponse(user.getId());
    }

    @Override
    public List<GetUserResponse> getAllUsers() {
        List<User> userList = userRepository.getAllUsers();
        List<GetUserResponse> userResponseList = new ArrayList<>();
        userList.forEach(user -> {
            GetUserResponse getUserResponse = UserAdapterUtil.convertToUserResponse(user);
            userResponseList.add(getUserResponse);
        });
        return userResponseList;
    }

    @Override
    public GetUserResponse getUserById(String userId) {
        User user = userRepository.getUserById(userId);
        return new GetUserResponse(user.getId(), user.getName(), user.getJoinedOn(), user.getContact(), user.getEmail(), user.getAddress());
    }

    @Override
    public List<GetProductReviewResponse> getAllReviewsOfUser(String userId) {
        User user = userRepository.getUserById(userId);
        List<ProductReview> productReviews = user.getProductReviews();

        return ReviewAdapterUtil.getProductReviewResponses(productReviews);
    }

    @Override
    public List<GetProductReviewResponse> getAllReviewsOfUserByCriteria(String userId, Integer offset, Integer limit) {
        User user = userRepository.getUserById(userId);
        List<ProductReview> productReviews = productRepository.getReviewsOfProduct(user, null, offset, limit);

        return ReviewAdapterUtil.getProductReviewResponses(productReviews);
    }
}
