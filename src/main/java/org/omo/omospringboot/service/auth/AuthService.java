package org.omo.omospringboot.service.auth;

import jakarta.transaction.Transactional;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.omo.omospringboot.constant.ErrorCode;
import org.omo.omospringboot.constant.TokenType;
import org.omo.omospringboot.dto.auth.kakao.KakaoUserInfo;
import org.omo.omospringboot.dto.auth.login.LoginResponseDto;
import org.omo.omospringboot.dto.auth.tokenReissue.ReIssueTokenResponseDto;
import org.omo.omospringboot.entity.user.User;
import org.omo.omospringboot.exception.CustomErrorException;
import org.omo.omospringboot.provider.JwtTokenProvider;
import org.omo.omospringboot.repository.user.UserRepository;
import org.springframework.data.redis.core.RedisTemplate;
import org.springframework.data.redis.core.ValueOperations;
import org.springframework.stereotype.Service;
import org.springframework.web.reactive.function.client.ClientResponse;
import org.springframework.web.reactive.function.client.WebClient;
import reactor.core.publisher.Mono;

@Service
@RequiredArgsConstructor
@Transactional
@Slf4j
public class AuthService {
    private final UserRepository userRepository;
    private final JwtTokenProvider jwtTokenProvider;
    private final RedisTemplate<String, String> redisTemplate;

    public LoginResponseDto login(String kakaoAccessToken) {
        KakaoUserInfo kakaoUserInfo = getUserInfoByKakao(kakaoAccessToken);
        User user = userRepository.findByUserProfileId(kakaoUserInfo.getId()).orElseGet(() -> userRepository.save(User.of(kakaoUserInfo)));

        String accessToken = jwtTokenProvider.generateAccessToken(user.getUserProfileId());
        String refreshToken = jwtTokenProvider.generateRefreshToken();

        ValueOperations<String, String> valueOperations = redisTemplate.opsForValue();
        valueOperations.set(refreshToken, accessToken);

        return LoginResponseDto.of(accessToken, refreshToken);
    }

    // 카카오 유저 정보를 가져옴
    private KakaoUserInfo getUserInfoByKakao(String accessToken) {
        WebClient webClient = WebClient.builder().build();
        String url = "https://kapi.kakao.com/v2/user/me";

        return webClient.get()
                .uri(url)  // Replace with your actual URL
                .header(
                        "Authorization",
                        accessToken
                )
                .retrieve()
                .onStatus(status -> status.value() == 401,
                        this::handle401Error)
                .onStatus(status -> status.value() == 403,
                        this::handle403Error)
                .bodyToMono(KakaoUserInfo.class)
                .block();
    }

    private Mono<? extends Throwable> handle401Error(ClientResponse response) {
        return response.bodyToMono(String.class)
                .flatMap(errorBody -> Mono.error(new CustomErrorException(ErrorCode.UnauthorizedKakaoError)));
    }

    private Mono<? extends Throwable> handle403Error(ClientResponse response) {
        return response.bodyToMono(String.class)
                .flatMap(errorBody -> Mono.error(new CustomErrorException(ErrorCode.ForbiddenKakaoError)));

    }

    public void logout(String refreshToken) {
        ValueOperations<String, String> valueOperations = redisTemplate.opsForValue();

        String resolvedRefreshToken = jwtTokenProvider.resolveToken(refreshToken);

        if (resolvedRefreshToken == null) {
            throw new CustomErrorException(ErrorCode.NotValidRequestError);
        }

        String savedAccessToken = valueOperations.get(resolvedRefreshToken);
        if (savedAccessToken == null) {
            throw new CustomErrorException(ErrorCode.NoSuchRefreshTokenError);
        }

        valueOperations.getAndDelete(resolvedRefreshToken);
    }

    public ReIssueTokenResponseDto reIssueToken(String accessToken, String refreshToken) {
        ValueOperations<String, String> valueOperations = redisTemplate.opsForValue();

        String resolvedAccessToken = jwtTokenProvider.resolveToken(accessToken);
        String resolvedRefreshToken = jwtTokenProvider.resolveToken(refreshToken);

        log.info("accessToken: " + resolvedAccessToken);
        log.info("refreshToken: " + resolvedRefreshToken);

        if (resolvedAccessToken == null || resolvedRefreshToken == null) {
            throw new CustomErrorException(ErrorCode.NotValidRequestError);
        }

        String savedAccessToken = valueOperations.get(resolvedRefreshToken);
        if (savedAccessToken == null) {
            throw new CustomErrorException(ErrorCode.NoSuchRefreshTokenError);
        }

        // RefreshToken 유효성 및 만료여부 확인
        boolean isExpiredRefreshToken = jwtTokenProvider.isExpiredToken(TokenType.REFRESH_TOKEN, resolvedRefreshToken);
        if (isExpiredRefreshToken) {
            valueOperations.getAndDelete(resolvedRefreshToken);
            throw new CustomErrorException(ErrorCode.ExpiredRefreshTokenError);
        }

        if (!resolvedAccessToken.equals(savedAccessToken)) {
            // RefreshToken이 탈취 당한 것으로 판단
            valueOperations.getAndDelete(resolvedRefreshToken);
            throw new CustomErrorException(ErrorCode.NoSuchAccessTokenError);
        }

        // AccessToken 유효성 및 만료여부 확인
        boolean isExpiredAccessToken = jwtTokenProvider.isExpiredToken(TokenType.ACCESS_TOKEN, resolvedAccessToken);
        if (!isExpiredAccessToken) {
            // RefreshToken이 탈취 당한 것으로 판단
            valueOperations.getAndDelete(resolvedRefreshToken);
            throw new CustomErrorException(ErrorCode.NotExpiredAccessTokenError);
        }

        String reIssuedAccessToken = jwtTokenProvider.reIssueAccessToken(resolvedAccessToken);
        valueOperations.getAndDelete(resolvedRefreshToken);
        valueOperations.set(resolvedRefreshToken, reIssuedAccessToken);
        return ReIssueTokenResponseDto.of(reIssuedAccessToken);
    }
}
