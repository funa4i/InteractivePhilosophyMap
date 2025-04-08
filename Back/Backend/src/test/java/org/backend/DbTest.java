package org.backend;


import org.backend.domain.data.bindings.DirectionData;
import org.backend.domain.data.bindings.HumanData;
import org.backend.domain.data.bindings.MuseumData;
import org.backend.domain.entity.Museum;
import org.backend.domain.repositories.HumanRepository;
import org.backend.domain.repositories.MuseumRepository;
import org.backend.managers.DirectionManager;
import org.backend.managers.HumanManager;
import org.backend.managers.MuseumManager;
import org.junit.jupiter.api.BeforeAll;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.json.JsonTest;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.util.Assert;

import java.util.Arrays;
import java.util.Objects;

import static org.assertj.core.api.FactoryBasedNavigableListAssert.assertThat;

@SpringBootTest
public class DbTest {
    @Autowired
    HumanManager humanManager;
    @Autowired
    MuseumManager museumManager;
    @Autowired
    DirectionManager directionManager;

    @BeforeEach
    public void clearAll(@Autowired HumanRepository humanRepository,
                         @Autowired MuseumRepository museumRepository){
        museumRepository.deleteAll();
        humanRepository.deleteAll();
    }
    @Test
    public void Save_Test(){
        humanManager.save(new HumanData(null, "some", "some", "some", 0, 0, 0d, 0d, null, null, null));
    }

    @Test
    public void Find_ByPeriod_Test(){
        var human = new HumanData(null, "some1", "some1", "some1", 10, 100, 0d, 0d, null, null, null);
        humanManager.save(human);
        var res = humanManager.getByPeriod(0, 110);
        Assert.state(Objects.equals(human.getIconPath(), res.get(0).getIconPath()), "must be equals");
    }

    @Test
    void PendingMuseumWithPresented(){
        var direction = new DirectionData(null, "", "", "", 10, 1d, 1d, null, null);
        var human = new HumanData(null, "some", "some", "some", 0, 0, 0d, 0d, null, null, null);
        var dV = directionManager.save(direction);
        var hV = humanManager.save(human);


        var museum = new MuseumData(null, "ttest", "", "", 10, 1d, 1d,
                Arrays.asList(hV.getId()), Arrays.asList(dV.getId()));
        var mv = museumManager.save(museum);
        Assert.state(mv.getDirectionRepresentative().size() != 0, "not empty");
        Assert.state(mv.getHumanRepresentative().size() != 0, "not empty");
        var tryGet = museumManager.getById(mv.getId());
        Assert.state(tryGet.getDirectionRepresentative().size() != 0, "not empty");
        Assert.state(tryGet.getHumanRepresentative().size() != 0, "not empty");

        var hT = humanManager.getById(hV.getId());
        var dT = directionManager.getById(dV.getId());
        Assert.state(hT.getMuseumPresented().size() != 0, "not empty");
        Assert.state(dT.getMuseumPresented().size() != 0, "not empty");

        var human2 = new HumanData(null, "some2", "some", "some", 0, 0, 0d, 0d, Arrays.asList(hV.getId()), null, null);
        var hV2 = humanManager.save(human2);

        hT = humanManager.getById(hV.getId());
        Assert.state(hT.getFollowHumans().size() != 0, "not empty");

        hT = humanManager.getById(hV2.getId());
        Assert.state(hT.getHumanFollowers().size() != 0, "not empty");
    }

}
