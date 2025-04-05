package org.backend.controllers;

import lombok.RequiredArgsConstructor;
import org.backend.domain.data.bindings.DirectionData;
import org.backend.domain.data.bindings.HumanData;
import org.backend.domain.data.views.DirectionView;
import org.backend.domain.data.views.HumanView;
import org.backend.managers.DirectionManager;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequiredArgsConstructor
public class DirectionController {
    private final DirectionManager directionManager;

    @GetMapping("/directions/{id}")
    public DirectionView getById(@PathVariable Long id){
        return directionManager.getById(id);
    }

    @PostMapping("/directions")
    public DirectionView create(@RequestBody DirectionData data){
        return  directionManager.save(data);
    }

    @PutMapping("/directions")
    public DirectionView update(@RequestBody DirectionData data){
        return directionManager.save(data);
    }

    @DeleteMapping("/directions/{id}")
    public void delete(@PathVariable Long id){
        directionManager.delete(id);
    }

    @GetMapping("/directions")
    public List<DirectionView> ByTime(@RequestParam Integer start, // ?start=
                                            @RequestParam Integer end){  // &end=
        return  directionManager.getByPeriod(start, end);
    }
}
