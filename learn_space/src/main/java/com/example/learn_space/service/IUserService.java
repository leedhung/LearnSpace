package com.example.learn_space.service;


import com.example.learn_space.dto.request.UserLoginRequest;
import com.example.learn_space.dto.response.UserResponse;
import com.example.learn_space.dto.request.UserCreationRequest;


public interface IUserService {
    UserResponse createUser(UserCreationRequest request);
    UserResponse login(UserLoginRequest request) throws Exception;
}
