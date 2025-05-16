package com.example.learn_space.models;

import jakarta.persistence.*;
import lombok.*;

@Entity
@Table(name = "materials")
@Getter
@Setter
@AllArgsConstructor
@NoArgsConstructor
@Builder
public class Materials {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    private String link;
    private String type;

    @ManyToOne
    @JoinColumn(name = "idPost")
    private ClassRoom classRoom;

}
