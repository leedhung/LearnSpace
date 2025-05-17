package com.example.learn_space.service.imp;


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
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.stereotype.Service;

@RequiredArgsConstructor
@Service
@FieldDefaults(level = AccessLevel.PRIVATE, makeFinal = true)
@Slf4j
public class UserService implements IUserService {
    private UserRepository userRepository;
    private UserMapper userMapper;
    private PasswordEncoder passwordEncoder;

    public UserResponse createUser(UserCreationRequest request) {
        User user = userMapper.toUser(request);
        user.setPassword(passwordEncoder.encode(request.getPassword()));

        try {
            user = userRepository.save(user);
        } catch (DataIntegrityViolationException exception) {
            throw new AppException(ErrorCode.USER_EXISTED);
        }

        return userMapper.toUserResponse(user);
    }


    @Override
    public String login(String phoneNumber, String password, Long roleId) throws Exception {
        return "";
    }
}
