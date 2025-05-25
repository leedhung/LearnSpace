package com.example.learn_space.service;

import com.example.learn_space.models.Lesson;

public interface IMaterialsService {
    boolean createMaterials(String link, String type, Long lessonId, Lesson lesson);
    String getLink(Long id);
}
