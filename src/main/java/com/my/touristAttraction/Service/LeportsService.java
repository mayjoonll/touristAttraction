package com.my.touristAttraction.Service;

import com.my.touristAttraction.entity.Leports;
import com.my.touristAttraction.repository.LeportsRepository;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
@RequiredArgsConstructor
public class LeportsService {

    private final LeportsRepository repository;

    public List<Leports> getAllLeports() {
        return repository.findAll();
    }

    public Leports saveLeports(Leports spot) {
        return repository.save(spot);
    }

    public void deleteLeports(Long id) {
        repository.deleteById(id);
    }
}