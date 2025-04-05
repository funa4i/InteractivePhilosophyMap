package org.backend.domain.data.bindings;

import java.time.LocalDate;
import java.util.List;

public class HumanData {
    private Long id; // может быть null если создаем новое

    private String iconPath;

    private String name;

    private String description;

    private LocalDate bornDate;

    private LocalDate dieDate;

    private Double x;

    private Double y;

    private List<Long> humanFollowers; // Наследники мышления

    private List<Long> followHumans; // Чье мышление наследует

    private List<Long> museumPresentedId; // В каких музеях представлены
}
