package org.backend.mappers;

import org.backend.domain.data.bindings.HumanData;
import org.backend.domain.data.views.HumanView;
import org.backend.domain.data.views.ShortInfo;
import org.backend.domain.entity.Human;
import org.backend.domain.entity.Museum;
import org.mapstruct.Mapper;
import org.mapstruct.Mapping;
import org.mapstruct.MappingConstants;
import org.mapstruct.Named;

import java.util.List;

@Mapper(componentModel = MappingConstants.ComponentModel.SPRING)

public interface HumanMapper {

    @Mapping(target = "humanFollowers", ignore = true)
    @Mapping(target = "followHumans", ignore = true)
    @Mapping(target = "museumPresented", ignore = true)
    Human toHuman(HumanData humanData);

    @Mapping(target = "humanFollowers", qualifiedByName = "mapHumans")
    @Mapping(target = "followHumans",  qualifiedByName = "mapHumans")
    @Mapping(target = "museumPresented", qualifiedByName = "mapMuseums")
    HumanView toHumanView(Human human);


    @Named("mapHumans")
    default List<ShortInfo> map1(List<Human> humans){
       return humans.stream().map((x) -> new ShortInfo(x.getId(), x.getName())).toList();
    }
    @Named("mapMuseums")
    default List<ShortInfo> map2(List<Museum> museums){
        return museums.stream().map((x) -> new ShortInfo(x.getId(), x.getName())).toList();
    }

}
