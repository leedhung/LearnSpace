package com.example.learn_space.controller;

import com.example.learn_space.dto.response.ApiResponse;
import com.example.learn_space.dto.response.Response;

import com.example.learn_space.dto.response.UserResponse;
import com.example.learn_space.dto.request.UserCreationRequest;
import com.example.learn_space.service.imp.UserService;
import com.fasterxml.jackson.core.JsonProcessingException;
import jakarta.validation.Valid;
import org.springframework.web.bind.annotation.*;

@RestController
@RequestMapping("/users")
public class UserController {
    UserService userService;
    @PostMapping("/register")
    @RequestMapping(value = "", method = RequestMethod.POST)

    ApiResponse createUser(@RequestBody @Valid UserCreationRequest request) throws JsonProcessingException {
        return ApiResponse.<UserResponse>builder()
                .result(userService.createUser(request))
                .build();
    }
}
