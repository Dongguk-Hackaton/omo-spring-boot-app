package org.omo.omospringboot.controller.taste;

import jakarta.validation.Valid;
import lombok.RequiredArgsConstructor;
import org.omo.omospringboot.dto.taste.TasteSaveRequestDto;
import org.omo.omospringboot.dto.taste.TasteSaveResponseDto;
import org.omo.omospringboot.entity.User;
import org.omo.omospringboot.service.taste.TasteService;
import org.springframework.http.HttpMethod;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.security.core.annotation.AuthenticationPrincipal;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequiredArgsConstructor
@RequestMapping("/api/tastes")
public class TasteController {
    private final TasteService tasteService;

    @PostMapping()
    public ResponseEntity<TasteSaveResponseDto> saveTaste(@AuthenticationPrincipal User user,
                                                          @Valid @RequestBody TasteSaveRequestDto requestDto){
        tasteService.saveTaste(user, requestDto);
        return new ResponseEntity<>(new TasteSaveResponseDto(), HttpStatus.CREATED);
    }
}
