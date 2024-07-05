package org.omo.omospringboot.dto.auth.kakao;

import com.fasterxml.jackson.annotation.JsonProperty;
import lombok.Getter;
import lombok.Setter;

import java.time.ZonedDateTime;

@Getter
@Setter
public class KakaoUserInfo {
    @JsonProperty("id")
    private Long id;

    @JsonProperty("connected_at")
    private ZonedDateTime connectedAt;

    @JsonProperty("properties")
    private Properties properties;

    @JsonProperty("kakao_account")
    private KakaoAccount kakaoAccount;
}