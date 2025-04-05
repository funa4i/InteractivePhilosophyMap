package org.backend.domain.data.views;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.time.LocalDate;
import java.util.List;
@Data
@AllArgsConstructor
@NoArgsConstructor
public class HumanView {
    private Long id; // может быть null если создаем новое

    private String iconPath;

    private String name;

    private String description;

    private Integer bornYear;

    private Integer dieYear;

    private Double x;

    private Double y;

    private List<ShortInfo> humanFollowers; // Наследники мышления

    private List<ShortInfo> followHumans; // Чье мышление наследует

    private List<ShortInfo> museumPresentedId; // В каких музеях представлены
}
