package com.my.touristAttraction.Service;

import com.my.touristAttraction.dto.JoinDto;
import com.my.touristAttraction.dto.UserRole;
import com.my.touristAttraction.entity.UserEntity;
import com.my.touristAttraction.repository.UserRepository;
import lombok.RequiredArgsConstructor;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.stereotype.Service;

@Service
@RequiredArgsConstructor
public class JoinService {

    private final UserRepository userRepository;
    private final BCryptPasswordEncoder bCryptPasswordEncoder;

    public boolean joinProcess(JoinDto joinDto) {
        boolean isUser = userRepository.existsByUsername(joinDto.getUsername());
        if (isUser) {
            return false;
        }
        UserEntity newUser = UserEntity.builder()
                .username(joinDto.getUsername())
                .password(bCryptPasswordEncoder.encode(joinDto.getPassword()))
                .email(joinDto.getEmail())
                .nickname(joinDto.getNickname())
                .name(joinDto.getName())
                .role(joinDto.getRole() == null ? UserRole.USER : joinDto.getRole())
                .build();

        userRepository.save(newUser);
        return true;
    }
}