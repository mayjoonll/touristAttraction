package com.my.touristAttraction.controller;

import com.my.touristAttraction.service.TouristSpotService;
import lombok.RequiredArgsConstructor;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequiredArgsConstructor
public class TouristSpotController {

    private final TouristSpotService service;

    @GetMapping("/tourist-spots/fetch")
    public String fetchTouristSpots() {
        service.fetchAndSaveTouristSpots();
        return "TouristSpot data fetched and saved!";
    }
}
