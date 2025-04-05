package org.backend.domain.data.views;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.time.LocalDate;
import java.util.List;

@Data
@AllArgsConstructor
@NoArgsConstructor
public class MuseumView {
    private Long id; // может быть null если создаем новое

    private String iconPath;

    private String name;

    private String description;

    private Integer openYear;

    private Double x;

    private Double y;

    private List<ShortInfo> humanRepresentative; // Кто представлен в музее

    private List<ShortInfo> directionRepresentative; // Какие направления представлены
}
