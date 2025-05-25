package com.example.learn_space.controller;

import com.example.learn_space.dto.request.UserLoginRequest;
import com.example.learn_space.dto.response.ApiResponse;
import com.example.learn_space.dto.response.Response;

import com.example.learn_space.dto.response.UserResponse;
import com.example.learn_space.dto.request.UserCreationRequest;
import com.example.learn_space.service.imp.UserService;
import com.fasterxml.jackson.core.JsonProcessingException;
import jakarta.validation.Valid;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

@RestController
@RequestMapping("/users")

public class UserController {
    @Autowired
    UserService userService;

    @PostMapping("/register")
    ApiResponse createUser(@RequestBody @Valid UserCreationRequest request)  {

        return ApiResponse.<UserResponse>builder()
                .result(userService.createUser(request))
                .build();
    }
    @PostMapping("/log-in")
    ApiResponse login(@RequestBody @Valid UserLoginRequest request) throws Exception {

        return ApiResponse.<UserResponse>builder()
                .code(0)
                .message("Login successful")
                .result(userService.login(request))
                .build();
    }
}
