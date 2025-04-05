package org.backend.managers;

import lombok.RequiredArgsConstructor;
import org.backend.domain.data.bindings.HumanData;
import org.backend.domain.data.views.HumanView;
import org.backend.domain.entity.Human;
import org.backend.domain.repositories.DirectionRepository;
import org.backend.domain.repositories.HumanRepository;
import org.backend.domain.repositories.MuseumRepository;
import org.backend.mappers.HumanMapper;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.ArrayList;
import java.util.List;

@Service
@RequiredArgsConstructor
public class HumanManager {
    private final HumanMapper mapper;
    private final HumanRepository humanRepository;
    private final MuseumRepository museumRepository;

    @Transactional
    public HumanView save(HumanData humanData){
        var user = mapper.toHuman(humanData);
        if (humanData.getHumanFollowers() != null){
            var ls =  humanData.getHumanFollowers().stream().map((x) -> humanRepository.findById(x).get()).toList();
            user.setHumanFollowers(ls);
        }
        else {
            user.setHumanFollowers(new ArrayList<>());
        }
        if (humanData.getFollowHumans() != null){
            var ls = humanData.getFollowHumans().stream().map((x) -> humanRepository.findById(x).get()).toList();
            user.setFollowHumans(ls);
        }
        else {
            user.setFollowHumans(new ArrayList<>());
        }
        if (humanData.getMuseumPresented() != null){
            var ls = humanData.getMuseumPresented().stream().map((x) -> museumRepository.findById(x).get()).toList();
            user.setMuseumPresented(ls);
        }
        else {
            user.setMuseumPresented(new ArrayList<>());
        }
        humanRepository.save(user);
        return mapper.toHumanView(user);
    }

    @Transactional
    public void  delete(Long id){
        humanRepository.deleteById(id);
    }
    @Transactional
    public HumanView getById(Long id){
        return mapper.toHumanView(humanRepository.findById(id).get());
    }
    @Transactional
    public List<HumanView> getByPeriod(Integer start, Integer end){
        var res = humanRepository.findByBornYearBetweenOrDieYearBetween(start, end, start, end);
        return res.stream().map(mapper::toHumanView).toList();
    }
}
