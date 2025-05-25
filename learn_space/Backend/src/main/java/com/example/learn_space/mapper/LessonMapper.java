package com.example.learn_space.mapper;

import com.example.learn_space.dto.request.LessonCreationRequest;
import com.example.learn_space.dto.response.LessonResponse;
import com.example.learn_space.models.Lesson;
import org.mapstruct.Mapper;
import org.mapstruct.Mapping;

@Mapper(componentModel = "spring")
public interface LessonMapper {
    @Mapping(target = "author", ignore = true)
    @Mapping(target = "classRoom", ignore = true)
    Lesson toLesson(LessonCreationRequest request);

    @Mapping(target = "authorId", ignore = true)
    @Mapping(source = "name", target = "name")
    LessonResponse toLessonResponse(Lesson lesson);
}
