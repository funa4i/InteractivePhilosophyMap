package org.backend.controllers;

import lombok.RequiredArgsConstructor;
import org.backend.domain.data.bindings.DirectionData;
import org.backend.domain.data.bindings.HumanData;
import org.backend.domain.data.bindings.MuseumData;
import org.backend.domain.data.views.HumanView;
import org.backend.domain.data.views.MuseumView;
import org.backend.managers.DirectionManager;
import org.backend.managers.MuseumManager;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequiredArgsConstructor
public class MuseumController {
    private final MuseumManager museumManager;
    @GetMapping("/museums/{id}")
    public MuseumView getById(@PathVariable Long id){
        return museumManager.getById(id);
    }

    @PostMapping("/museums")
    public MuseumView create(@RequestBody MuseumData data){
        return  museumManager.save(data);
    }

    @PutMapping("/museums")
    public MuseumView update(@RequestBody MuseumData data){
        return museumManager.save(data);
    }

    @DeleteMapping("/museums/{id}")
    public void delete(@PathVariable Long id){
        museumManager.delete(id);
    }

    @GetMapping("/museums")
    public List<MuseumView> ByTime(@RequestParam Integer start, // ?start=
                                        @RequestParam Integer end){  // &end=
        return  museumManager.getByPeriod(start, end);
    }
}
