package org.backend.domain.entity;

import jakarta.persistence.Column;
import jakarta.persistence.Entity;
import jakarta.persistence.Id;
import jakarta.persistence.ManyToMany;
import lombok.AllArgsConstructor;
import lombok.Data;

import java.time.LocalDate;
import java.util.List;

@Data
@Entity
@AllArgsConstructor
public class Direction {

    @Id
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
    private Double x;

    @Column
    private Double y;

    @ManyToMany
    private List<Human> humanFollowers; // id наследников

    @ManyToMany
    private List<Museum> museumPresented; // в каких музея представлены
}
