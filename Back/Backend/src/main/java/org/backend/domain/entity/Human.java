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
public class Human {

    @Id
    @GeneratedValue(strategy = GenerationType.SEQUENCE)
    private Long id;

    @Column
    private String iconPath;

    @Column
    private String name;

    @Column
    private String description;

    @Column
    private Integer bornYear;

    @Column
    private Integer dieYear;

    @Column
    private Double x;

    @Column
    private Double y;

    @ManyToMany(mappedBy = "followHumans")
    private List<Human> humanFollowers; // Наследники мышления

    @ManyToMany
    private List<Human> followHumans; // Чье мышление наследует

    @ManyToMany
    private List<Museum> museumPresented; // В каких музеях представлены
}
