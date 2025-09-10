package com.my.touristAttraction.Service;

import com.my.touristAttraction.entity.TouristSpot;
import com.my.touristAttraction.repository.TouristSpotRepository;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
@RequiredArgsConstructor
public class TouristSpotService {

    private final TouristSpotRepository repository;

    public List<TouristSpot> getAllTouristSpots() {
        return repository.findAll();
    }

    public TouristSpot saveTouristSpot(TouristSpot spot) {
        return repository.save(spot);
    }

    public void deleteTouristSpot(Long id) {
        repository.deleteById(id);
    }
}