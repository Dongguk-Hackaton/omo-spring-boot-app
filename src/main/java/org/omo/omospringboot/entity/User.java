package org.omo.omospringboot.entity;

import jakarta.persistence.*;
import lombok.*;
import org.omo.omospringboot.dto.auth.kakao.KakaoUserInfo;
import org.springframework.data.annotation.CreatedDate;
import org.springframework.data.annotation.LastModifiedDate;
import org.springframework.data.jpa.domain.support.AuditingEntityListener;
import org.springframework.security.core.GrantedAuthority;
import org.springframework.security.oauth2.core.user.OAuth2User;

import java.time.LocalDateTime;
import java.util.Collection;
import java.util.Collections;
import java.util.Map;


/**
 * 회원번호
 * 실명
 * 닉네임
 * 성별
 * 나이
 * 프로필 사진
 */

@Entity
@Table(name = "Users")
@Getter
@NoArgsConstructor(access = AccessLevel.PROTECTED)
@AllArgsConstructor
@Builder
@EntityListeners(AuditingEntityListener.class)
public class User implements OAuth2User {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    @Column(nullable = false, unique = true)
    private Long userProfileId;

    @Column(nullable = false, unique = true)
    private String nickname;

    @Column(nullable = false)
    private String profileImage;

    @CreatedDate
    private LocalDateTime createdAt;

    @LastModifiedDate
    private LocalDateTime updatedAt;

    private String authority;

    @Override
    public Map<String, Object> getAttributes() {
        return null;
    }

    @Override
    public Collection<? extends GrantedAuthority> getAuthorities() {
        return Collections.singleton((GrantedAuthority) () -> authority);
    }

    @Override
    public String getName() {
        return nickname;
    }

    public static User of(KakaoUserInfo kakaoUserInfo) {
        return User.builder()
                .userProfileId(kakaoUserInfo.getId())
                .nickname(kakaoUserInfo.getProperties().getNickname())
                .profileImage(kakaoUserInfo.getProperties().getProfileImage())
                .authority("ROLE_USER")
                .build();
    }
}
