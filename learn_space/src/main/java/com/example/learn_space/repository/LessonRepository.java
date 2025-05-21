package com.example.learn_space.repository;

import com.example.learn_space.models.ClassRoom;
import com.example.learn_space.models.Lesson;
import org.springframework.data.jpa.repository.JpaRepository;

public interface LessonRepository extends JpaRepository<Lesson, Long> {
}
