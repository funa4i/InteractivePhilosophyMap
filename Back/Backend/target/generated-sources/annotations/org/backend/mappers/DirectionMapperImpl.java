package org.backend.mappers;

import javax.annotation.processing.Generated;
import org.backend.domain.data.bindings.DirectionData;
import org.backend.domain.data.views.DirectionView;
import org.backend.domain.entity.Direction;
import org.springframework.stereotype.Component;

@Generated(
    value = "org.mapstruct.ap.MappingProcessor",
    date = "2025-04-06T16:18:54+0400",
    comments = "version: 1.6.3, compiler: javac, environment: Java 18.0.2.1 (Oracle Corporation)"
)
@Component
public class DirectionMapperImpl implements DirectionMapper {

    @Override
    public Direction toDirection(DirectionData directionData) {
        if ( directionData == null ) {
            return null;
        }

        Direction direction = new Direction();

        direction.setId( directionData.getId() );
        direction.setIconPath( directionData.getIconPath() );
        direction.setName( directionData.getName() );
        direction.setDescription( directionData.getDescription() );
        direction.setBornYear( directionData.getBornYear() );
        direction.setX( directionData.getX() );
        direction.setY( directionData.getY() );

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
