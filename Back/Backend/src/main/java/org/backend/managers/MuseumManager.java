package org.backend.managers;

import lombok.RequiredArgsConstructor;
import org.backend.domain.data.bindings.DirectionData;
import org.backend.domain.data.bindings.MuseumData;
import org.backend.domain.data.views.DirectionView;
import org.backend.domain.data.views.MuseumView;
import org.backend.domain.repositories.DirectionRepository;
import org.backend.domain.repositories.HumanRepository;
import org.backend.domain.repositories.MuseumRepository;
import org.backend.mappers.DirectionMapper;
import org.backend.mappers.MuseumMapper;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.ArrayList;
import java.util.List;

@Service
@RequiredArgsConstructor
public class MuseumManager {
    private final MuseumMapper mapper;
    private final DirectionRepository directionRepository;
    private final MuseumRepository museumRepository;
    private final HumanRepository humanRepository;

    @Transactional
    public MuseumView save(MuseumData museumData){
        var museum = mapper.toMuseum(museumData);
        if (museumData.getDirectionRepresentative() != null){
            museum.setDirectionRepresentative(museumData.getDirectionRepresentative().stream()
                    .map(x -> directionRepository.findById(x).get()).toList());
        }
        else {
            museum.setDirectionRepresentative(new ArrayList<>());
        }
        if (museumData.getHumanRepresentative() != null){
            museum.setHumanRepresentative(museumData.getHumanRepresentative().stream()
                    .map(x -> humanRepository.findById(x).get()).toList());
        }
        else {
            museum.setHumanRepresentative(new ArrayList<>());
        }
        museumRepository.save(museum);
        return mapper.toMuseumView(museum);
    }

    @Transactional
    public void  delete(Long id){
        museumRepository.deleteById(id);
    }
    @Transactional
    public MuseumView getById(Long id){
        var res = museumRepository.findById(id).get();
        return mapper.toMuseumView(res);
    }

    @Transactional
    public List<MuseumView> getByPeriod(Integer start, Integer end){
        return museumRepository.findByOpenYearBetween(start, end)
                .stream().map(mapper::toMuseumView).toList();

    }
}
