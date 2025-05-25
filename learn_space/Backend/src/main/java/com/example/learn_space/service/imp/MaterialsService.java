package com.example.learn_space.service.imp;

import com.example.learn_space.exceptions.AppException;
import com.example.learn_space.exceptions.ErrorCode;
import com.example.learn_space.models.Lesson;
import com.example.learn_space.models.Materials;
import com.example.learn_space.repository.LessonRepository;
import com.example.learn_space.repository.MaterialsRepository;
import com.example.learn_space.service.IMaterialsService;
import lombok.AccessLevel;
import lombok.RequiredArgsConstructor;
import lombok.experimental.FieldDefaults;
import lombok.extern.slf4j.Slf4j;
import org.springframework.dao.DataIntegrityViolationException;
import org.springframework.stereotype.Service;

@RequiredArgsConstructor
@Service
@FieldDefaults(level = AccessLevel.PRIVATE, makeFinal = true)
@Slf4j
public class MaterialsService implements IMaterialsService {
    MaterialsRepository materialsRepository;
    //6.9.4	MaterialsService sẽ dùng  link được gửi để lưu xuống db
    @Override
    public boolean createMaterials(String link, String type,Long lessonId, Lesson lesson) {
        Materials materials = new Materials();
        materials.setLink(link);
        materials.setType(type);
        materials.setLesson(lesson);
        try{
            materialsRepository.save(materials);
        } catch (DataIntegrityViolationException exception){
            throw new AppException(ErrorCode.INVALID_KEY);
        }
        return true;
    }

    @Override
    public String getLink(Long id) {
        return "";
    }
}
