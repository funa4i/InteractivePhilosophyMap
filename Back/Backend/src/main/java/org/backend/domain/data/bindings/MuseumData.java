package org.backend.domain.data.bindings;

import java.time.LocalDate;
import java.util.List;

public class MuseumData {
    private Long id; // может быть null если создаем новое

    private String iconPath;

    private String name;

    private String description;

    private LocalDate openData;

    private Double x;

    private Double y;

    private List<Long> humanRepresentative; // Кто представлен в музее

    private List<Long> directionRepresentative; // Какие направления представлены
}
