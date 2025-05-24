package com.example.learn_space.controller;

import com.example.learn_space.dto.request.ClassCreationRequest;
import com.example.learn_space.dto.response.ApiResponse;
import com.example.learn_space.dto.response.ClassRoomResponse;
import com.example.learn_space.dto.response.UserResponse;
import com.example.learn_space.service.imp.ClassRoomService;
import com.example.learn_space.service.imp.UserService;
import jakarta.validation.Valid;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequestMapping("/class")
public class ClassroomController {
    @Autowired
    ClassRoomService classRoomService;

    @PostMapping("/create-class")
    ApiResponse createClas(@RequestBody @Valid ClassCreationRequest request )  {

        return ApiResponse.<ClassRoomResponse>builder()
                .result(classRoomService.createClass(request))
                .build();
    }

}
