package com.example.learn_space.controller;

import com.example.learn_space.dto.request.ClassCreationRequest;
import com.example.learn_space.dto.request.LessonCreationRequest;
import com.example.learn_space.dto.response.ApiResponse;
import com.example.learn_space.dto.response.ClassRoomResponse;
import com.example.learn_space.dto.response.LessonResponse;
import com.example.learn_space.service.imp.LessonService;
import jakarta.validation.Valid;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

@RestController
@RequestMapping("/lesson")
public class LessonController {
    @Autowired
    LessonService lessonService;
    //6.8 Phương thức sẽ gửi một request Post tới server thông qua
    // http://localhost:8088/lesson/create-lesson
    @PostMapping("/create-lesson")
    ApiResponse createLesson(@ModelAttribute @Valid LessonCreationRequest request)  {
        //6.9 Trên server sẽ  nhận lấy request và gọi service sử lý
        //6.10	Sau khi hoàn tất server sẽ gửi lại một response
        return ApiResponse.<LessonResponse>builder()
                .result(lessonService.createLesson(request))
                .build();
    }

}
