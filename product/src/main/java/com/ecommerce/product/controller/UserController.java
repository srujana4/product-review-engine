package com.ecommerce.product.controller;

import com.ecommerce.product.dto.request.AddUserRequest;
import com.ecommerce.product.dto.response.AddUserResponse;
import com.ecommerce.product.dto.response.GetProductReviewResponse;
import com.ecommerce.product.dto.response.GetUserResponse;
import com.ecommerce.product.service.UserManagementService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

import java.util.List;

@RestController
@RequestMapping(value = "/users")
public class UserController {

    @Autowired
    private UserManagementService userManagementService;

    @GetMapping(value="/{userId}", produces = "application/json")
    public ResponseEntity<GetUserResponse> getProductById(@PathVariable("userId") String userId){
        GetUserResponse response = userManagementService.getUserById(userId);
        return new ResponseEntity<>(response, HttpStatus.OK);
    }

    @PostMapping(value = "", produces = "application/json")
    public ResponseEntity<AddUserResponse> createProduct(@RequestBody AddUserRequest addUserRequest)
    {
        return new ResponseEntity<>( userManagementService.addNewUser(addUserRequest), HttpStatus.CREATED );
    }

    @GetMapping(value="", produces = "application/json")
    public ResponseEntity<List<GetUserResponse>> getAllUsers(){
        List<GetUserResponse> response = userManagementService.getAllUsers();
        return new ResponseEntity<>(response, HttpStatus.OK);
    }

    @GetMapping(value="/{userId}/reviews", produces = "application/json")
    public ResponseEntity<List<GetProductReviewResponse>> getAllReviewsOfUser(
            @PathVariable("userId") String userId, @RequestParam(value = "offset", required = false) Integer offset,
            @RequestParam(value = "limit", required = false) Integer limit){

        List<GetProductReviewResponse> productReviewResponseList =
                userManagementService.getAllReviewsOfUserByCriteria(userId, offset, limit);
        return new ResponseEntity<>(productReviewResponseList, HttpStatus.OK);
    }
}