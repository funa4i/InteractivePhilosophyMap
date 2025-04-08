package org.backend.mappers;


import org.backend.domain.data.bindings.MuseumData;
import org.backend.domain.data.views.MuseumView;
import org.backend.domain.data.views.ShortInfo;
import org.backend.domain.entity.Direction;
import org.backend.domain.entity.Human;
import org.backend.domain.entity.Museum;
import org.mapstruct.Mapper;
import org.mapstruct.Mapping;
import org.mapstruct.MappingConstants;
import org.mapstruct.Named;

import java.util.List;

@Mapper(componentModel = MappingConstants.ComponentModel.SPRING)
public interface MuseumMapper {

    @Mapping(target = "humanRepresentative",ignore = true)
    @Mapping(target = "directionRepresentative", ignore = true)
    Museum toMuseum(MuseumData museumData);

    @Mapping(target = "humanRepresentative",qualifiedByName = "mapHumans")
    @Mapping(target = "directionRepresentative", qualifiedByName = "mapDirections")
    MuseumView toMuseumView(Museum museum);


    @Named("mapHumans")
    default List<ShortInfo> map1(List<Human> humans){
        return humans.stream().map((x) -> new ShortInfo(x.getId(), x.getName())).toList();
    }
    @Named("mapDirections")
    default List<ShortInfo> map2(List<Direction> directions){
        return directions.stream().map((x) -> new ShortInfo(x.getId(), x.getName())).toList();
    }
}
