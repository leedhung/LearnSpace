package com.example.learn_space.service;

import com.example.learn_space.dto.request.LessonCreationRequest;
import com.example.learn_space.dto.response.LessonResponse;

public interface ILessonService {
    LessonResponse createLesson(LessonCreationRequest request);
    LessonResponse getLessonById(Long id);
}
