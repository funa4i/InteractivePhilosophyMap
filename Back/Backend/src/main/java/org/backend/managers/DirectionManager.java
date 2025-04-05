package org.backend.managers;

import lombok.RequiredArgsConstructor;
import org.backend.domain.data.bindings.DirectionData;
import org.backend.domain.data.bindings.HumanData;
import org.backend.domain.data.views.DirectionView;
import org.backend.domain.data.views.HumanView;
import org.backend.domain.repositories.DirectionRepository;
import org.backend.domain.repositories.HumanRepository;
import org.backend.domain.repositories.MuseumRepository;
import org.backend.mappers.DirectionMapper;
import org.backend.mappers.HumanMapper;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.ArrayList;
import java.util.List;

@Service
@RequiredArgsConstructor
public class DirectionManager {
    private final DirectionMapper mapper;
    private final DirectionRepository directionRepository;
    private final MuseumRepository museumRepository;
    private final HumanRepository humanRepository;

    @Transactional
    public DirectionView save(DirectionData directionData){
        var direction = mapper.toDirection(directionData);
        if (directionData.getHumanFollowers() != null) {
            direction.setHumanFollowers(directionData.getHumanFollowers().stream()
                    .map(x -> humanRepository.findById(x).get()).toList());
        }
        else {
            direction.setHumanFollowers(new ArrayList<>());
        }
        if (directionData.getMuseumPresented() != null){
            direction.setMuseumPresented(directionData.getMuseumPresented().stream()
                    .map(x -> museumRepository.findById(x).get()).toList());

        }
        else {
            direction.setMuseumPresented(new ArrayList<>());
        }
        directionRepository.save(direction);
        return mapper.toDirectionView(direction);
    }

    @Transactional
    public void  delete(Long id){
        directionRepository.deleteById(id);
    }
    @Transactional
    public DirectionView getById(Long id){
        return mapper.toDirectionView(directionRepository.findById(id).get());
    }

    @Transactional
    public List<DirectionView> getByPeriod(Integer start, Integer end){
        return directionRepository.findByBornYearBetween(start, end)
                .stream().map(mapper::toDirectionView).toList();

    }
}
