package org.backend.domain.data.bindings;

import java.time.LocalDate;
import java.util.List;

public class DirectionData {
    private Long id; // может быть null если создаем новое

    private String iconPath;

    private String name;

    private String description;

    private LocalDate bornDate;

    private Double x;

    private Double y;

    private List<Long> humanFollowersId; // id наследников

    private List<Long> museumPresentedId; // в каких музея представлены
}
