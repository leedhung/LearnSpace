package com.example.learn_space.service.imp;

import com.example.learn_space.constain.RoleType;
import com.example.learn_space.dto.request.LessonCreationRequest;
import com.example.learn_space.dto.response.LessonResponse;
import com.example.learn_space.exceptions.AppException;
import com.example.learn_space.exceptions.ErrorCode;
import com.example.learn_space.mapper.LessonMapper;
import com.example.learn_space.models.ClassRoom;
import com.example.learn_space.models.Lesson;
import com.example.learn_space.models.User;
import com.example.learn_space.repository.ClassRoomRepository;
import com.example.learn_space.repository.LessonRepository;
import com.example.learn_space.repository.UserRepository;
import com.example.learn_space.service.ILessonService;
import lombok.AccessLevel;
import lombok.RequiredArgsConstructor;
import lombok.experimental.FieldDefaults;
import lombok.extern.slf4j.Slf4j;
import org.springframework.dao.DataIntegrityViolationException;
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
     ClassRoomRepository classRoomRepository;
    @Override
    public LessonResponse createLesson(LessonCreationRequest request) {

        Lesson lesson = lessonMapper.toLesson(request);
        User user = userRepository.findById(request.getAuthorId()).orElseThrow(() -> new AppException(ErrorCode.CLASS_NOT_EXIT));
        ClassRoom classRoom = classRoomRepository.findById(request.getClassId()).orElseThrow(() -> new AppException(ErrorCode.CLASS_NOT_EXIT));

        if(!user.getRole().equals(RoleType.ROLE_ADMIN))  throw new AppException(ErrorCode.NOT_ENOUGH_RIGHTS);
        lesson.setClassRoom(classRoom);
        lesson.setAuthor(user);
        try {
            //6.9.1	Service lấy bài học được  khởi tạo để lưu xuống db
            lesson = lessonRepository.save(lesson);
        }catch (DataIntegrityViolationException exception){
            throw new AppException(ErrorCode.LESSON_EXISTED);
        }

        LessonResponse lessonResponse = lessonMapper.toLessonResponse(lesson);
        String link,type;
        for (MultipartFile file : request.getFiles()) {
            type = file.getContentType();
            //6.9.2 Service kiểm tra số lượng file có trong request để gửi lên cloud lưu trữ và nhận lại link
            link = cloudinaryService.uploadFile(file);
            //6.9.3	Khi đã có link của của file  gửi tới MaterialsService
            if(!materialsService.createMaterials(link,type,request.getId(), lesson))
               lessonResponse.setLoss(" Can't upload file \" "+file.getName()+" \" ");
        }
        lessonResponse.setName(lesson.getName());
        lessonResponse.setAuthorId(user.getId());
        lessonResponse.setCreatAt(request.getCreatAt());
        return lessonResponse;
    }

    @Override
    public LessonResponse getLessonById(Long id) {
        Lesson lesson = lessonRepository.findById(id).orElseThrow(() -> new AppException(ErrorCode.LESSON_NOT_EXIT));

        return null;
    }
}
