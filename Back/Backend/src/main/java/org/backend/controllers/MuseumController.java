package org.backend.controllers;

import org.backend.domain.data.bindings.HumanData;
import org.backend.domain.data.views.HumanView;
import org.backend.domain.data.views.MuseumView;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
public class MuseumController {
    @GetMapping("/museums/{id}")
    public MuseumView getById(@PathVariable Long id){
        return null;
    }

    @PostMapping("/museums")
    public MuseumView create(@RequestBody HumanData data){
        return  null;
    }

    @PutMapping("/museums")
    public MuseumView update(@RequestBody HumanData data){
        return null;
    }

    @DeleteMapping("/museums/{id}")
    public void delete(@PathVariable Long id){

    }

    @GetMapping("/museums")
    public List<MuseumView> humansByTime(@RequestParam Integer start, // ?start=
                                        @RequestParam Integer end){  // &end=
        return  null;
    }
}
