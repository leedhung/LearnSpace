package com.example.learn_space.service;

import com.example.learn_space.dto.request.AuthenticationRequest;

public interface IAuthenticationService {
    boolean authenticate(AuthenticationRequest request);
}
