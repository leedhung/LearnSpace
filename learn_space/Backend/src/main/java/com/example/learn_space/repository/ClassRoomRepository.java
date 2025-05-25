package com.example.learn_space.repository;

import com.example.learn_space.models.ClassRoom;
import com.example.learn_space.models.User;
import org.springframework.data.jpa.repository.JpaRepository;

public interface ClassRoomRepository extends JpaRepository<ClassRoom, Long> {
}
