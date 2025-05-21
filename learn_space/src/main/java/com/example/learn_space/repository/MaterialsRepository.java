package com.example.learn_space.repository;

import com.example.learn_space.models.ClassRoom;
import com.example.learn_space.models.Materials;
import org.springframework.data.jpa.repository.JpaRepository;

public interface MaterialsRepository extends JpaRepository<Materials, Long> {
}
