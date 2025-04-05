package org.backend.domain.data.bindings;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.time.LocalDate;
import java.util.List;
@Data
@AllArgsConstructor
@NoArgsConstructor
public class MuseumData {
    private Long id; // может быть null если создаем новое

    private String iconPath;

    private String name;

    private String description;

    private Integer openYear;

    private Double x;

    private Double y;

    private List<Long> humanRepresentative; // Кто представлен в музее

    private List<Long> directionRepresentative; // Какие направления представлены
}
