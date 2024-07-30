package org.omo.omospringboot.dto.user;

import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Getter;
import lombok.Setter;
import org.omo.omospringboot.entity.user.User;

import java.time.LocalDateTime;

@Getter
@Setter
@AllArgsConstructor
@Builder
public class UserInfoResponseDto {
    private Long userProfileId;

    private String nickname;

    private String profileImage;

    private LocalDateTime createdAt;

    private LocalDateTime updatedAt;

    public static UserInfoResponseDto fromEntity(User user) {
        return UserInfoResponseDto.builder()
                .userProfileId(user.getUserProfileId())
                .nickname(user.getNickname())
                .profileImage(user.getProfileImage())
                .createdAt(user.getCreatedAt())
                .updatedAt(user.getUpdatedAt())
                .build();
    }
}
