package org.backend.controllers;

import org.backend.domain.data.bindings.HumanData;
import org.backend.domain.data.views.DirectionView;
import org.backend.domain.data.views.HumanView;
import org.springframework.web.bind.annotation.*;

import java.util.List;

public class DirectionController {
    @GetMapping("/directions/{id}")
    public DirectionView getById(@PathVariable Long id){
        return null;
    }

    @PostMapping("/directions")
    public DirectionView create(@RequestBody HumanData data){
        return  null;
    }

    @PutMapping("/directions")
    public DirectionView update(@RequestBody HumanData data){
        return null;
    }

    @DeleteMapping("/directions/{id}")
    public void delete(@PathVariable Long id){

    }

    @GetMapping("/directions")
    public List<DirectionView> humansByTime(@RequestParam Integer start, // ?start=
                                            @RequestParam Integer end){  // &end=
        return  null;
    }
}
