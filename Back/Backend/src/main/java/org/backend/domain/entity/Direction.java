package org.backend.domain.entity;

import jakarta.persistence.*;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.time.LocalDate;
import java.util.List;

@Data
@Entity
@AllArgsConstructor
@NoArgsConstructor
public class Direction {

    @Id
    @GeneratedValue(strategy = GenerationType.SEQUENCE)
    private Long id;

    @Column
    private String iconPath;

    @Column
    private String name;

    @Column(length = 1000)
    private String description;

    @Column
    private Integer bornYear;

    @Column
    private Double x;

    @Column
    private Double y;

    @ManyToMany
    private List<Human> humanFollowers; // id наследников

    @ManyToMany(mappedBy = "directionRepresentative")
    private List<Museum> museumPresented; // в каких музея представлены
}
