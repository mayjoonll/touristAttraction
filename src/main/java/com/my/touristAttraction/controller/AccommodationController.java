package com.my.touristAttraction.controller;

import com.my.touristAttraction.Service.AccommodationService;
import com.my.touristAttraction.entity.Accommodation;
import lombok.RequiredArgsConstructor;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/accommodation")
@RequiredArgsConstructor
public class AccommodationController {

    private final AccommodationService service;

    @GetMapping
    public List<Accommodation> getAll() {
        return service.getAllAccommodation();
    }

    @PostMapping
    public Accommodation create(@RequestBody Accommodation spot) {
        return service.saveTouristSpot(spot);
    }

    @DeleteMapping("/{id}")
    public void delete(@PathVariable Long id) {
        service.deleteTouristSpot(id);
    }
}
