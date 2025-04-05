package org.backend.domain.data.views;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.time.LocalDate;
import java.util.List;

@Data
@AllArgsConstructor
@NoArgsConstructor
public class DirectionView {
    private Long id; // может быть null если создаем новое

    private String iconPath;

    private String name;

    private String description;

    private Integer bornYear;

    private Double x;

    private Double y;

    private List<ShortInfo> humanFollowersId; // id наследников

    private List<ShortInfo> museumPresentedId; // в каких музея представлены
}
