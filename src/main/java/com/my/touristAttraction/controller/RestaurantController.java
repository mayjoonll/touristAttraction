package com.my.touristAttraction.controller;

import com.my.touristAttraction.Service.RestaurantService;
import com.my.touristAttraction.entity.Restaurant;
import lombok.RequiredArgsConstructor;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/restaurant")
@RequiredArgsConstructor
public class RestaurantController {

    private final RestaurantService service;

    @GetMapping
    public List<Restaurant> getAll() {
        return service.getAllRestaurant();
    }

    @PostMapping
    public Restaurant create(@RequestBody Restaurant spot) {
        return service.saveRestaurant(spot);
    }

    @DeleteMapping("/{id}")
    public void delete(@PathVariable Long id) {
        service.deleteRestaurant(id);
    }
}
