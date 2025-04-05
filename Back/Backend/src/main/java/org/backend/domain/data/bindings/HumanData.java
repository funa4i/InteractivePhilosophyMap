package org.backend.domain.data.bindings;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.time.LocalDate;
import java.util.List;

@Data
@AllArgsConstructor
@NoArgsConstructor
public class HumanData {
    private Long id; // может быть null если создаем новое

    private String iconPath;

    private String name;

    private String description;

    private Integer bornYear;

    private Integer dieYear;

    private Double x;

    private Double y;

    private List<Long> humanFollowers; // Наследники мышления

    private List<Long> followHumans; // Чье мышление наследует

    private List<Long> museumPresented; // В каких музеях представлены
}
