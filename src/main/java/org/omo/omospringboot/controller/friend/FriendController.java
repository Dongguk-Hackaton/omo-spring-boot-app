package org.omo.omospringboot.controller.friend;

import jakarta.validation.Valid;
import lombok.RequiredArgsConstructor;
import org.omo.omospringboot.dto.friend.*;
import org.omo.omospringboot.entity.user.User;
import org.omo.omospringboot.service.friend.FriendService;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.security.core.annotation.AuthenticationPrincipal;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/api/friends")
@RequiredArgsConstructor
public class FriendController {

    private final FriendService friendService;

    @PostMapping
    public ResponseEntity<FriendSaveResponseDto> saveFriend(@AuthenticationPrincipal User user,
                                                            @Valid @RequestBody FriendSaveRequestDto requestDto) {

        friendService.saveFriend(user, requestDto);
        return new ResponseEntity<>(new FriendSaveResponseDto(), HttpStatus.CREATED);
    }

    @GetMapping
    public ResponseEntity<List<FriendGetResponseDto>> getFriend(@AuthenticationPrincipal User user) {

        List<FriendGetResponseDto> friends = friendService.getFriend(user);
        return new ResponseEntity<>(friends, HttpStatus.OK);
    }

    @GetMapping("/{friendId}")
    public ResponseEntity<FriendDetailGetResponseDto> detailFriend(@AuthenticationPrincipal User user,
                                                                   @PathVariable Long friendId) {

        FriendDetailGetResponseDto friendDetailGetResponseDto = friendService.detailFriend(user, friendId);
        return new ResponseEntity<>(friendDetailGetResponseDto, HttpStatus.OK);
    }

    @DeleteMapping("/{friendId}/delete")
    public ResponseEntity<FriendDeleteResponseDto> deleteFriend(@AuthenticationPrincipal User user,
                                                                @PathVariable Long friendId) {

        FriendDeleteResponseDto friendDeleteResponseDto = friendService.deleteFriend(user, friendId);
        return new ResponseEntity<>(friendDeleteResponseDto, HttpStatus.OK);
    }
}
