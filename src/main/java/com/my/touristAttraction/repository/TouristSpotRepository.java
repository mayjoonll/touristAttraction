package com.my.touristAttraction.repository;


import com.my.touristAttraction.entity.TouristSpot;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;
@Repository
public interface TouristSpotRepository extends JpaRepository<TouristSpot, Long> {
    boolean existsByContentid(String contentid); // contentid 기준 중복 체크
}