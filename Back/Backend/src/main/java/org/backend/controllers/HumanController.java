package org.backend.controllers;

import org.backend.domain.data.bindings.HumanData;
import org.backend.domain.data.views.HumanView;
import org.springframework.web.bind.annotation.*;

import java.time.LocalDate;
import java.util.List;

@RestController
public class HumanController {

    @GetMapping("/humans/{id}")
    public HumanView getById(@PathVariable Long id){
        return null;
    }

    @PostMapping("/humans")
    public HumanView create(@RequestBody HumanData data){
        return  null;
    }

    @PutMapping("/humans")
    public HumanView update(@RequestBody HumanData data){
        return null;
    }

    @DeleteMapping("/humans/{id}")
    public void delete(@PathVariable Long id){

    }

    @GetMapping("/humans")
    public List<HumanView> humansByTime(@RequestParam Integer start, // ?start=
                                        @RequestParam Integer end){  // &end=
        return  null;
    }
}
