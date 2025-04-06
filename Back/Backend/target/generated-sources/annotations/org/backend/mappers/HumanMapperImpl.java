package org.backend.mappers;

import javax.annotation.processing.Generated;
import org.backend.domain.data.bindings.HumanData;
import org.backend.domain.data.views.HumanView;
import org.backend.domain.entity.Human;
import org.springframework.stereotype.Component;

@Generated(
    value = "org.mapstruct.ap.MappingProcessor",
    date = "2025-04-06T16:18:54+0400",
    comments = "version: 1.6.3, compiler: javac, environment: Java 18.0.2.1 (Oracle Corporation)"
)
@Component
public class HumanMapperImpl implements HumanMapper {

    @Override
    public Human toHuman(HumanData humanData) {
        if ( humanData == null ) {
            return null;
        }

        Human human = new Human();

        human.setId( humanData.getId() );
        human.setIconPath( humanData.getIconPath() );
        human.setName( humanData.getName() );
        human.setDescription( humanData.getDescription() );
        human.setBornYear( humanData.getBornYear() );
        human.setDieYear( humanData.getDieYear() );
        human.setX( humanData.getX() );
        human.setY( humanData.getY() );

        return human;
    }

    @Override
    public HumanView toHumanView(Human human) {
        if ( human == null ) {
            return null;
        }

        HumanView humanView = new HumanView();

        humanView.setHumanFollowers( map1( human.getHumanFollowers() ) );
        humanView.setFollowHumans( map1( human.getFollowHumans() ) );
        humanView.setMuseumPresented( map2( human.getMuseumPresented() ) );
        humanView.setId( human.getId() );
        humanView.setIconPath( human.getIconPath() );
        humanView.setName( human.getName() );
        humanView.setDescription( human.getDescription() );
        humanView.setBornYear( human.getBornYear() );
        humanView.setDieYear( human.getDieYear() );
        humanView.setX( human.getX() );
        humanView.setY( human.getY() );

        return humanView;
    }
}
