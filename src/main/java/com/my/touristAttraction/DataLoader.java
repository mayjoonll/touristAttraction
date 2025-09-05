package com.my.touristAttraction;

import com.my.touristAttraction.service.AccommodationService;
import org.springframework.boot.CommandLineRunner;
import org.springframework.stereotype.Component;
import lombok.RequiredArgsConstructor;

@Component
@RequiredArgsConstructor
public class DataLoader implements CommandLineRunner {

    private final AccommodationService accommodationService;

    @Override
    public void run(String... args) throws Exception {
        accommodationService.fetchAndSaveAccommodations();
    }
}
