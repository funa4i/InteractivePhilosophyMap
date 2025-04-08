package org.backend.domain.entity;

import jakarta.persistence.*;
import jdk.jfr.Timestamp;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.time.LocalDate;
import java.util.List;
@Data
@Entity
@AllArgsConstructor
@NoArgsConstructor
public class Museum {

    @Id
    @GeneratedValue(strategy = GenerationType.SEQUENCE)
    private Long id;

    @Column
    private String iconPath;

    @Column(unique = true)
    private String name;

    @Column(length = 1000)
    private String description;

    @Column
    private Integer openYear;

    @Column
    private Double x;

    @Column
    private Double y;

    @ManyToMany
    private List<Human> humanRepresentative; // Кто представлен в музее

    @ManyToMany
    private List<Direction> directionRepresentative; // Какие направления представлены
}
