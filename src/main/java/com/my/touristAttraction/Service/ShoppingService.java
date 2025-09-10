package com.my.touristAttraction.Service;

import com.my.touristAttraction.entity.Shopping;
import com.my.touristAttraction.repository.ShoppingRepository;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
@RequiredArgsConstructor
public class ShoppingService {

    private final ShoppingRepository repository;

    public List<Shopping> getAllShopping() {
        return repository.findAll();
    }

    public Shopping saveShopping(Shopping spot) {
        return repository.save(spot);
    }

    public void deleteShopping(Long id) {
        repository.deleteById(id);
    }
}