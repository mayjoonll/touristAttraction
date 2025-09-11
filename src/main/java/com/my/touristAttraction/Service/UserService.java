package com.my.touristAttraction.Service;

import com.my.touristAttraction.dto.UserDto;
import com.my.touristAttraction.entity.UserEntity;
import com.my.touristAttraction.repository.UserRepository;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;
import org.springframework.util.ObjectUtils;

import java.util.List;
import java.util.stream.Collectors;

@Service
@RequiredArgsConstructor
public class UserService {
    private final UserRepository userRepository;

    public void saveUser(UserDto dto) {
        UserEntity entity = UserDto.toEntity(dto);
        userRepository.save(entity);
    }

    public List<UserDto> findAllUsers() {
        List<UserEntity> entities = userRepository.findAll();
        return entities.stream()
                .map(UserDto::fromEntity)
                .collect(Collectors.toList());
    }

    public void deleteUsers(String email) {
        userRepository.deleteByEmail(email);
    }

    public UserDto findOneUsers(String email) {
        UserEntity entity = userRepository.findByEmail(email).orElse(null);
        if (ObjectUtils.isEmpty(entity)) {
            return null;
        }
        return UserDto.fromEntity(entity);
    }
}

