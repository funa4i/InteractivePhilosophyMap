package org.backend.mappers;

import java.util.List;
import javax.annotation.processing.Generated;
import org.backend.domain.data.bindings.HumanData;
import org.backend.domain.data.views.HumanView;
import org.backend.domain.entity.Human;
import org.backend.domain.entity.Museum;
import org.springframework.stereotype.Component;

@Generated(
    value = "org.mapstruct.ap.MappingProcessor",
    date = "2025-04-06T01:43:31+0400",
    comments = "version: 1.6.3, compiler: javac, environment: Java 18.0.2.1 (Oracle Corporation)"
)
@Component
public class HumanMapperImpl implements HumanMapper {

    @Override
    public Human toHuman(HumanData humanData) {
        if ( humanData == null ) {
            return null;
        }

        Long id = null;
        String iconPath = null;
        String name = null;
        String description = null;
        Integer bornYear = null;
        Integer dieYear = null;
        Double x = null;
        Double y = null;

        id = humanData.getId();
        iconPath = humanData.getIconPath();
        name = humanData.getName();
        description = humanData.getDescription();
        bornYear = humanData.getBornYear();
        dieYear = humanData.getDieYear();
        x = humanData.getX();
        y = humanData.getY();

        List<Human> humanFollowers = null;
        List<Human> followHumans = null;
        List<Museum> museumPresented = null;

        Human human = new Human( id, iconPath, name, description, bornYear, dieYear, x, y, humanFollowers, followHumans, museumPresented );

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
