package org.backend.mappers;

import javax.annotation.processing.Generated;
import org.backend.domain.data.bindings.MuseumData;
import org.backend.domain.data.views.MuseumView;
import org.backend.domain.entity.Museum;
import org.springframework.stereotype.Component;

@Generated(
    value = "org.mapstruct.ap.MappingProcessor",
    date = "2025-04-06T16:18:54+0400",
    comments = "version: 1.6.3, compiler: javac, environment: Java 18.0.2.1 (Oracle Corporation)"
)
@Component
public class MuseumMapperImpl implements MuseumMapper {

    @Override
    public Museum toMuseum(MuseumData museumData) {
        if ( museumData == null ) {
            return null;
        }

        Museum museum = new Museum();

        museum.setId( museumData.getId() );
        museum.setIconPath( museumData.getIconPath() );
        museum.setName( museumData.getName() );
        museum.setDescription( museumData.getDescription() );
        museum.setOpenYear( museumData.getOpenYear() );
        museum.setX( museumData.getX() );
        museum.setY( museumData.getY() );

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
