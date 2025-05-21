package com.example.learn_space.service.imp;

import com.example.learn_space.constain.RoleType;
import com.example.learn_space.dto.request.LessonCreationRequest;
import com.example.learn_space.dto.response.LessonResponse;
import com.example.learn_space.exceptions.AppException;
import com.example.learn_space.exceptions.ErrorCode;
import com.example.learn_space.mapper.LessonMapper;
import com.example.learn_space.models.Lesson;
import com.example.learn_space.models.User;
import com.example.learn_space.repository.LessonRepository;
import com.example.learn_space.repository.UserRepository;
import com.example.learn_space.service.ILessonService;
import lombok.AccessLevel;
import lombok.RequiredArgsConstructor;
import lombok.experimental.FieldDefaults;
import lombok.extern.slf4j.Slf4j;
import org.springframework.stereotype.Service;
import org.springframework.web.multipart.MultipartFile;

@RequiredArgsConstructor
@Service
@FieldDefaults(level = AccessLevel.PRIVATE, makeFinal = true)
@Slf4j
public class LessonService implements ILessonService {
     LessonMapper lessonMapper;
     LessonRepository lessonRepository;
     UserRepository userRepository;
     MaterialsService materialsService;
     CloudinaryService cloudinaryService;
    @Override
    public LessonResponse createLesson(LessonCreationRequest request) {
        Lesson lesson = lessonMapper.toLesson(request);
        User user = userRepository.findById(request.getOwnerId()).orElseThrow(() -> new AppException(ErrorCode.CLASS_NOT_EXIT));
        if(!user.getRole().equals(RoleType.ROLE_ADMIN))  throw new AppException(ErrorCode.NOT_ENOUGH_RIGHTS);
        String link,type;
        for (MultipartFile file : request.getFiles()) {
            type = file.getContentType();
            link = cloudinaryService.uploadFile(file);
            materialsService.createMaterials(link,type,request.getId(), lesson);
        }
        LessonResponse lessonResponse = lessonMapper.toLessonResponse(lesson);
        lessonResponse.setAuthorId(user.getId());
        return lessonResponse;
    }

    @Override
    public LessonResponse getLessonById(Long id) {
        Lesson lesson = lessonRepository.findById(id).orElseThrow(() -> new AppException(ErrorCode.LESSON_NOT_EXIT));

        return null;
    }
}
