package org.omo.omospringboot.controller.auth;

import lombok.RequiredArgsConstructor;
import lombok.Value;
import org.omo.omospringboot.dto.auth.login.LoginResponseDto;
import org.omo.omospringboot.dto.auth.logout.LogoutResponseDto;
import org.omo.omospringboot.dto.auth.tokenReissue.ReIssueTokenResponseDto;
import org.omo.omospringboot.service.auth.AuthService;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestHeader;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequiredArgsConstructor
@RequestMapping("/api/auth")
public class AuthController {

    private final AuthService authService;

    @PostMapping("/login")
    public ResponseEntity<LoginResponseDto> login(
            @RequestHeader(required = true, name = "Authorization") String accessToken
    ) {
        LoginResponseDto loginResponseDto = authService.login(accessToken);

        return new ResponseEntity<>(loginResponseDto, HttpStatus.CREATED);
    }

    @PostMapping("/logout")
    public ResponseEntity<LogoutResponseDto> logout(
            @RequestHeader("Authorization-refresh") String refreshToken
    ) {
        authService.logout(refreshToken);

        return new ResponseEntity<>(new LogoutResponseDto(), HttpStatus.CREATED);
    }

    @PostMapping("/reissue/token")
    public ResponseEntity<ReIssueTokenResponseDto> reIssueToken(
            @RequestHeader("Authorization") String accessToken,
            @RequestHeader("Authorization-refresh") String refreshToken
    ){

        ReIssueTokenResponseDto reIssueTokenResponseDto = authService.reIssueToken(accessToken, refreshToken);
        return new ResponseEntity<>(reIssueTokenResponseDto, HttpStatus.CREATED);
    }
}
