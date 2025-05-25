package com.example.learn_space.mapper;

import com.example.learn_space.dto.request.ClassCreationRequest;
import com.example.learn_space.dto.request.UserCreationRequest;
import com.example.learn_space.dto.response.ClassRoomResponse;
import com.example.learn_space.dto.response.UserResponse;
import com.example.learn_space.models.ClassRoom;
import com.example.learn_space.models.User;
import com.example.learn_space.repository.UserRepository;
import com.example.learn_space.service.imp.UserService;
import org.mapstruct.Mapper;
import org.mapstruct.Mapping;
import org.mapstruct.MappingTarget;
import org.mapstruct.Named;

@Mapper(componentModel = "spring")
public interface ClassRoomMapper {

    @Mapping(target = "owner", ignore = true)
    ClassRoom toClassRoom(ClassCreationRequest request);

    @Mapping(target = "ownerId", ignore = true)
    ClassRoomResponse toClassRoomResponse(ClassRoom classRoom);


}

