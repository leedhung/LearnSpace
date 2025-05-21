package com.example.learn_space.controller;

import com.example.learn_space.dto.request.ClassCreationRequest;
import com.example.learn_space.dto.request.LessonCreationRequest;
import com.example.learn_space.dto.response.ApiResponse;
import com.example.learn_space.dto.response.ClassRoomResponse;
import com.example.learn_space.dto.response.LessonResponse;
import com.example.learn_space.service.imp.LessonService;
import jakarta.validation.Valid;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequestMapping("/Lesson")
public class LessonController {
    @Autowired
    LessonService lessonService;

    @PostMapping("/create-lesson")
    ApiResponse createLesson(@RequestBody @Valid LessonCreationRequest request)  {
        return ApiResponse.<LessonResponse>builder()
                .result(lessonService.createLesson(request))
                .build();
    }

}
