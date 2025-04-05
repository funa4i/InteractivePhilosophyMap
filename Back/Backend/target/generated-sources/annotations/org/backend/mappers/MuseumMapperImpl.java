package org.backend.mappers;

import java.util.List;
import javax.annotation.processing.Generated;
import org.backend.domain.data.bindings.MuseumData;
import org.backend.domain.data.views.MuseumView;
import org.backend.domain.entity.Direction;
import org.backend.domain.entity.Human;
import org.backend.domain.entity.Museum;
import org.springframework.stereotype.Component;

@Generated(
    value = "org.mapstruct.ap.MappingProcessor",
    date = "2025-04-06T01:43:31+0400",
    comments = "version: 1.6.3, compiler: javac, environment: Java 18.0.2.1 (Oracle Corporation)"
)
@Component
public class MuseumMapperImpl implements MuseumMapper {

    @Override
    public Museum toMuseum(MuseumData museumData) {
        if ( museumData == null ) {
            return null;
        }

        Long id = null;
        String iconPath = null;
        String name = null;
        String description = null;
        Integer openYear = null;
        Double x = null;
        Double y = null;

        id = museumData.getId();
        iconPath = museumData.getIconPath();
        name = museumData.getName();
        description = museumData.getDescription();
        openYear = museumData.getOpenYear();
        x = museumData.getX();
        y = museumData.getY();

        List<Human> humanRepresentative = null;
        List<Direction> directionRepresentative = null;

        Museum museum = new Museum( id, iconPath, name, description, openYear, x, y, humanRepresentative, directionRepresentative );

        return museum;
    }

    @Override
    public MuseumView toMuseumView(Museum museum) {
        if ( museum == null ) {
            return null;
        }

        MuseumView museumView = new MuseumView();

        museumView.setHumanRepresentative( map1( museum.getHumanRepresentative() ) );
        museumView.setDirectionRepresentative( map2( museum.getDirectionRepresentative() ) );
        museumView.setId( museum.getId() );
        museumView.setIconPath( museum.getIconPath() );
        museumView.setName( museum.getName() );
        museumView.setDescription( museum.getDescription() );
        museumView.setOpenYear( museum.getOpenYear() );
        museumView.setX( museum.getX() );
        museumView.setY( museum.getY() );

        return museumView;
    }
}
