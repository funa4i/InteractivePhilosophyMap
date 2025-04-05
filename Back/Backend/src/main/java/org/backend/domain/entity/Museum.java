package org.backend.domain.entity;

import jakarta.persistence.Column;
import jakarta.persistence.Entity;
import jakarta.persistence.Id;
import jakarta.persistence.ManyToMany;
import jdk.jfr.Timestamp;
import lombok.AllArgsConstructor;
import lombok.Data;

import java.time.LocalDate;
import java.util.List;
@Data
@Entity
@AllArgsConstructor
public class Museum {
    @Id
    private Long id;

    @Column
    private String iconPath;

    @Column
    private String name;

    @Column
    private String description;

    @Column
    private Integer openYear;

    @Column
    private Double x;

    @Column
    private Double y;

    @ManyToMany(mappedBy = "museumPresented")
    private List<Human> humanRepresentative; // Кто представлен в музее

    @ManyToMany(mappedBy = "museumPresented")
    private List<Direction> directionRepresentative; // Какие направления представлены
}
