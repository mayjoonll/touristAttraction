package com.my.touristAttraction.Service;

import com.my.touristAttraction.entity.Restaurant;
import com.my.touristAttraction.repository.RestaurantRepository;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
@RequiredArgsConstructor
public class RestaurantService {

    private final RestaurantRepository repository;

    public List<Restaurant> getAllRestaurant() {
        return repository.findAll();
    }

    public Restaurant saveRestaurant(Restaurant spot) {
        return repository.save(spot);
    }

    public void deleteRestaurant(Long id) {
        repository.deleteById(id);
    }
}