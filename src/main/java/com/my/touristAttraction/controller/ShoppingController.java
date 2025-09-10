package com.my.touristAttraction.controller;

import com.my.touristAttraction.Service.ShoppingService;
import com.my.touristAttraction.entity.Shopping;
import lombok.RequiredArgsConstructor;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/shopping")
@RequiredArgsConstructor
public class ShoppingController {

    private final ShoppingService service;

    @GetMapping
    public List<Shopping> getAll() {
        return service.getAllShopping();
    }

    @PostMapping
    public Shopping create(@RequestBody Shopping spot) {
        return service.saveShopping(spot);
    }

    @DeleteMapping("/{id}")
    public void delete(@PathVariable Long id) {
        service.deleteShopping(id);
    }
}
