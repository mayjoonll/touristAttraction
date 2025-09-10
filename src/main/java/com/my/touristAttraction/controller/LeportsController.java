package com.my.touristAttraction.controller;

import com.my.touristAttraction.Service.LeportsService;
import com.my.touristAttraction.entity.Leports;
import lombok.RequiredArgsConstructor;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/leports")
@RequiredArgsConstructor
public class LeportsController {

    private final LeportsService service;

    @GetMapping
    public List<Leports> getAll() {
        return service.getAllLeports();
    }

    @PostMapping
    public Leports create(@RequestBody Leports spot) {
        return service.saveLeports(spot);
    }

    @DeleteMapping("/{id}")
    public void delete(@PathVariable Long id) {
        service.deleteLeports(id);
    }
}
