package com.example.learn_space.models;

import jakarta.persistence.*;
import lombok.*;

import java.time.LocalDateTime;

@Entity
@Table(name = "classrom")
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

    @Column(name= "code" , length = 200, nullable = false)
    private String code;

    @Column(name = "createdAt")
    private LocalDateTime createdAt;

    @ManyToOne
    @JoinColumn(name = "owner")
    private User owner;

}
