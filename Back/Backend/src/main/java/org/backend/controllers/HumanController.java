package org.backend.controllers;

import lombok.RequiredArgsConstructor;
import org.backend.domain.data.bindings.HumanData;
import org.backend.domain.data.views.HumanView;
import org.backend.managers.HumanManager;
import org.springframework.web.bind.annotation.*;

import java.time.LocalDate;
import java.util.List;

@RestController
@RequiredArgsConstructor
public class HumanController {
    private final HumanManager humanManager;

    @GetMapping("/humans/{id}")
    public HumanView getById(@PathVariable Long id){
        return humanManager.getById(id);
    }

    @PostMapping("/humans")
    public HumanView create(@RequestBody HumanData data){
        return humanManager.save(data);
    }

    @PutMapping("/humans")
    public HumanView update(@RequestBody HumanData data){
        return humanManager.save(data);
    }

    @DeleteMapping("/humans/{id}")
    public void delete(@PathVariable Long id){
        humanManager.delete(id);
    }

    @GetMapping("/humans")
    public List<HumanView> ByTime(@RequestParam Integer start, // ?start=
                                        @RequestParam Integer end){  // &end=
        return humanManager.getByPeriod(start, end);
    }
}
