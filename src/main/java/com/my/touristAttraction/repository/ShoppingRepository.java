package com.my.touristAttraction.repository;


import com.my.touristAttraction.entity.Shopping;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;
@Repository
public interface ShoppingRepository extends JpaRepository<Shopping, Long> {
    boolean existsByContentid(String contentid); // contentid 기준 중복 체크
}