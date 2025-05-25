package com.example.learn_space.mapper;

import org.mapstruct.Mapper;
import org.mapstruct.Mapping;
import org.mapstruct.MappingTarget;

import com.example.learn_space.dto.request.UserCreationRequest;
import com.example.learn_space.dto.response.UserResponse;
import com.example.learn_space.models.User;

@Mapper(componentModel = "spring")
public interface UserMapper {
    User toUser(UserCreationRequest request);

    UserResponse toUserResponse(User user);

}
