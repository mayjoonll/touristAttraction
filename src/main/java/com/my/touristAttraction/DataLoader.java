package com.my.touristAttraction;

import com.my.touristAttraction.service.TouristSpotService;
import org.springframework.boot.CommandLineRunner;
import org.springframework.stereotype.Component;
import lombok.RequiredArgsConstructor;

@Component
@RequiredArgsConstructor
public class DataLoader implements CommandLineRunner {

//    private final AccommodationService accommodationService;
    private final TouristSpotService touristSpotService;

    @Override
    public void run(String... args) throws Exception {
        // 숙박 데이터 저장
//        accommodationService.fetchAndSaveAccommodations();

        // 관광지 데이터 저장
        touristSpotService.fetchAndSaveTouristSpots();
    }
}
