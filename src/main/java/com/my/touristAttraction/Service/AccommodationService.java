package com.my.touristAttraction.Service;

import com.my.touristAttraction.entity.Accommodation;
import com.my.touristAttraction.repository.AccommodationRepository;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
@RequiredArgsConstructor
public class AccommodationService {

    private final AccommodationRepository repository;

    public List<Accommodation> getAllAccommodation() {
        return repository.findAll();
    }

    public Accommodation saveTouristSpot(Accommodation spot) {
        return repository.save(spot);
    }

    public void deleteTouristSpot(Long id) {
        repository.deleteById(id);
    }
}