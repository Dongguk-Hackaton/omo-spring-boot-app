package org.omo.omospringboot.controller.user;

import lombok.RequiredArgsConstructor;
import org.omo.omospringboot.dto.user.UserInfoResponseDto;
import org.omo.omospringboot.entity.User;
import org.omo.omospringboot.service.user.UserService;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.security.access.prepost.PreAuthorize;
import org.springframework.security.core.annotation.AuthenticationPrincipal;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;

@Controller
@RequiredArgsConstructor
@RequestMapping("/api/user")
public class UserController {
    private final UserService userService;

    @RequestMapping("/info")
    public ResponseEntity<UserInfoResponseDto> getUserInfo(@AuthenticationPrincipal User user) {
        UserInfoResponseDto userInfoResponseDto = userService.getUserInfo(user);
        return new ResponseEntity<>(userInfoResponseDto, HttpStatus.OK);
    }
}
