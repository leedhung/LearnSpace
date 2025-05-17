package com.example.learn_space.service;


import com.example.learn_space.dto.response.UserResponse;
import com.example.learn_space.dto.request.UserCreationRequest;


public interface IUserService {
    UserResponse createUser(UserCreationRequest request);
    String login(String phoneNumber, String password, Long roleId) throws Exception;
}
