package org.omo.omospringboot.dto.auth.tokenReissue;

import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Getter;
import lombok.Setter;

@Getter
@Setter
@AllArgsConstructor
@Builder
public class ReIssueTokenResponseDto {
    private String accessToken;

    public static ReIssueTokenResponseDto of(String reIssuedAccessToken) {
        return ReIssueTokenResponseDto.builder()
                .accessToken(reIssuedAccessToken)
                .build();
    }
}
