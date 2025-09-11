package com.my.touristAttraction.repository;

import com.my.touristAttraction.entity.UserEntity;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.Optional;

public interface UserRepository extends JpaRepository<UserEntity, Long> {
    Optional<UserEntity> findByEmail(String email);
    boolean existsByEmail(String email);

    void deleteByEmail(String email);

    boolean existsByUsername(String username);

    UserEntity findByUsername(String username);
}
