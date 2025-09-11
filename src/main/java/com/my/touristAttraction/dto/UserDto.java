package com.my.touristAttraction.dto;

import com.my.touristAttraction.entity.UserEntity;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.time.LocalDateTime;

@Data
@AllArgsConstructor
@NoArgsConstructor
@Builder
public class UserDto {
    private String name;
    private String nickname;
    private Long id;
    private String password;
    private String email;

    private LocalDateTime createdAt;
    private LocalDateTime updatedAt;

    public static UserDto fromEntity(UserEntity entity) {
        return new UserDto(
                entity.getName(),
                entity.getNickname(),
                entity.getId(),
                entity.getPassword(),
                entity.getEmail(),
                entity.getCreatedAt(),
                entity.getUpdatedAt()
        );
    }

    public static UserEntity toEntity(UserDto dto) {
        return UserEntity.builder()
                .id(dto.getId())
                .email(dto.getEmail())
                .nickname(dto.getNickname())
                .password(dto.getPassword())
                .name(dto.getName())
                .createdAt(dto.getCreatedAt())
                .updatedAt(dto.getUpdatedAt())
                .build();
    }

}

