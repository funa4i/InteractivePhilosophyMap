package org.backend.domain.entity;

import jakarta.persistence.Column;
import jakarta.persistence.Entity;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.ManyToMany;
import jdk.jfr.Timestamp;
import lombok.AllArgsConstructor;
import lombok.Data;
import org.springframework.data.annotation.Id;

import java.time.LocalDate;
import java.util.List;
@Data
@Entity
@AllArgsConstructor
public class Human {
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
