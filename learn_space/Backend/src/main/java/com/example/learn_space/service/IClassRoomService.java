package com.example.learn_space.service;

import com.example.learn_space.dto.request.ClassCreationRequest;
import com.example.learn_space.dto.response.ClassRoomResponse;

import java.util.List;

public interface IClassRoomService {
    ClassRoomResponse createClass(ClassCreationRequest request);
    ClassRoomResponse getClassById(Long id);
}
