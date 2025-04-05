package org.backend.mappers;

import java.util.List;
import javax.annotation.processing.Generated;
import org.backend.domain.data.bindings.DirectionData;
import org.backend.domain.data.views.DirectionView;
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
public class DirectionMapperImpl implements DirectionMapper {

    @Override
    public Direction toDirection(DirectionData directionData) {
        if ( directionData == null ) {
            return null;
        }

        Long id = null;
        String iconPath = null;
        String name = null;
        String description = null;
        Integer bornYear = null;
        Double x = null;
        Double y = null;

        id = directionData.getId();
        iconPath = directionData.getIconPath();
        name = directionData.getName();
        description = directionData.getDescription();
        bornYear = directionData.getBornYear();
        x = directionData.getX();
        y = directionData.getY();

        List<Human> humanFollowers = null;
        List<Museum> museumPresented = null;

        Direction direction = new Direction( id, iconPath, name, description, bornYear, x, y, humanFollowers, museumPresented );

        return direction;
    }

    @Override
    public DirectionView toDirectionView(Direction direction) {
        if ( direction == null ) {
            return null;
        }

        DirectionView directionView = new DirectionView();

        directionView.setHumanFollowers( map1( direction.getHumanFollowers() ) );
        directionView.setMuseumPresented( map2( direction.getMuseumPresented() ) );
        directionView.setId( direction.getId() );
        directionView.setIconPath( direction.getIconPath() );
        directionView.setName( direction.getName() );
        directionView.setDescription( direction.getDescription() );
        directionView.setBornYear( direction.getBornYear() );
        directionView.setX( direction.getX() );
        directionView.setY( direction.getY() );

        return directionView;
    }
}
