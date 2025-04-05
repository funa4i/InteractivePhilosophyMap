package org.backend.domain.data.bindings;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.time.LocalDate;
import java.util.List;
@Data
@AllArgsConstructor
@NoArgsConstructor
public class DirectionData {
    private Long id; // может быть null если создаем новое

    private String iconPath;

    private String name;

    private String description;

    private Integer bornYear;

    private Double x;

    private Double y;

    private List<Long> humanFollowers; // id наследников

    private List<Long> museumPresented; // в каких музея представлены
}
