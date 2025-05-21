package com.example.learn_space.service.imp;


import com.example.learn_space.constain.RoleType;
import com.example.learn_space.dto.request.UserLoginRequest;
import com.example.learn_space.dto.response.UserResponse;
import com.example.learn_space.dto.request.UserCreationRequest;
import com.example.learn_space.exceptions.AppException;
import com.example.learn_space.exceptions.ErrorCode;
import com.example.learn_space.mapper.UserMapper;
import com.example.learn_space.models.User;
import com.example.learn_space.repository.UserRepository;
import com.example.learn_space.service.IUserService;
import lombok.AccessLevel;
import lombok.RequiredArgsConstructor;
import lombok.experimental.FieldDefaults;
import lombok.extern.slf4j.Slf4j;
import org.springframework.dao.DataIntegrityViolationException;
//import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.stereotype.Service;

import java.util.Optional;

@RequiredArgsConstructor
@Service
@FieldDefaults(level = AccessLevel.PRIVATE, makeFinal = true)
@Slf4j
public class UserService implements IUserService {
     UserRepository userRepository;
     UserMapper userMapper;
//    private PasswordEncoder passwordEncoder;


    public UserResponse createUser(UserCreationRequest request) {
        User user = userMapper.toUser(request);
        user.setPassword(request.getPassword());
        user.setRole(request.getRole());
        user.setName(request.getName());

        try {
            user = userRepository.save(user);
        } catch (DataIntegrityViolationException exception) {
            throw new AppException(ErrorCode.USER_EXISTED);
        }

        return userMapper.toUserResponse(user);
    }


    @Override
    public UserResponse login(UserLoginRequest request) throws Exception {
        User user = userRepository
                .findByUsername(request.getUsername())
                .orElseThrow(() -> new AppException(ErrorCode.USER_NOT_EXISTED));

        if (!user.getPassword().equals(request.getPassword())) {
            throw new AppException(ErrorCode.INVALID_PASSWORD);
        }
        return UserResponse.builder()
                .id(user.getId())
                .username(user.getUsername())
                .name(user.getName())
                .role(user.getRole())
                .build();
    }
}
