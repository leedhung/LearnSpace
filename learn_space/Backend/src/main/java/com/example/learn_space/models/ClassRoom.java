package com.example.learn_space.models;

import jakarta.persistence.*;
import lombok.*;

import java.time.LocalDateTime;
import java.util.UUID;

@Entity
@Table(name = "classroom")
@Getter
@Setter
@AllArgsConstructor
@NoArgsConstructor
@Builder
public class ClassRoom {
    @Id
    @GeneratedValue
    private long id;

    @Column(name= "name" , length = 200, nullable = false)
    private String name;

    @Column(name = "code", unique = true)
    private String code;

    @Column(name = "create_at")
    private LocalDateTime createdAt;

    @ManyToOne
    @JoinColumn(name = "owner")
    private User owner;

    @PrePersist
    private void generateCode() {
        this.code = UUID.randomUUID().toString().substring(0, 8); // ví dụ tạo code ngắn 8 ký tự
    }
}
