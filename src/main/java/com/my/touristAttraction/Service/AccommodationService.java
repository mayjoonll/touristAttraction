package com.my.touristAttraction.Service;

import com.my.touristAttraction.entity.Accommodation;
import com.my.touristAttraction.entity.Leports;
import com.my.touristAttraction.repository.AccommodationRepository;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
@RequiredArgsConstructor
public class AccommodationService {

    private final AccommodationRepository repository;
    private final AccommodationRepository accommodationRepository;

    public List<Accommodation> getAllAccommodation() {
        return repository.findAll();
    }

    public Accommodation saveAccommodation(Accommodation accommodation) {
        return repository.save(accommodation);
    }

    public void deleteAccommodation(Long id) {
        repository.deleteById(id);
    }

    public Accommodation getAccommodationById(Long id) {
        return repository.findById(id)
                .orElseThrow(() -> new RuntimeException("숙소를 찾을 수 없습니다. id=" + id));
    }

    public List<Accommodation> getNearby(Double lat, Double lng, Double radiusKm) {
        return accommodationRepository.findAll().stream()
                .filter(s -> s.getMapy() != null && s.getMapx() != null)
                .filter(s -> haversine(lat, lng, s.getMapy(), s.getMapx()) <= radiusKm)
                .toList();
    }

    private double haversine(double lat1, double lon1, double lat2, double lon2) {
        final int R = 6371; // 지구 반지름 (km)
        double dLat = Math.toRadians(lat2 - lat1);
        double dLon = Math.toRadians(lon2 - lon1);
        double a = Math.sin(dLat/2) * Math.sin(dLat/2)
                + Math.cos(Math.toRadians(lat1)) * Math.cos(Math.toRadians(lat2))
                * Math.sin(dLon/2) * Math.sin(dLon/2);
        double c = 2 * Math.atan2(Math.sqrt(a), Math.sqrt(1-a));
        return R * c;
    }
}
