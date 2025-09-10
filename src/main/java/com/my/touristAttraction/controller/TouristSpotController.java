package com.my.touristAttraction.controller;

import com.my.touristAttraction.Service.TouristSpotService;
import com.my.touristAttraction.entity.TouristSpot;
import lombok.RequiredArgsConstructor;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/tourist-spots")
@RequiredArgsConstructor
public class TouristSpotController {

    private final TouristSpotService service;

    @GetMapping
    public List<TouristSpot> getAll() {
        return service.getAllTouristSpots();
    }

    @PostMapping
    public TouristSpot create(@RequestBody TouristSpot spot) {
        return service.saveTouristSpot(spot);
    }

    @DeleteMapping("/{id}")
    public void delete(@PathVariable Long id) {
        service.deleteTouristSpot(id);
    }
}
