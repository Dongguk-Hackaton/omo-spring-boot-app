package org.omo.omospringboot.controller.taste;

import jakarta.validation.Valid;
import lombok.RequiredArgsConstructor;
import org.omo.omospringboot.dto.taste.*;
import org.omo.omospringboot.entity.User;
import org.omo.omospringboot.service.taste.TasteService;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.security.core.annotation.AuthenticationPrincipal;
import org.springframework.web.bind.annotation.*;

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

    @GetMapping()
    public ResponseEntity<TasteGetResponseDto> getTaste(@AuthenticationPrincipal User user) {

        TasteGetResponseDto tasteGet = tasteService.getTaste(user);
        return new ResponseEntity<>(tasteGet, HttpStatus.OK);
    }

    @PutMapping()
    public ResponseEntity<TastePutResponseDto> putTaste(@AuthenticationPrincipal User user,
                                                          @Valid @RequestBody TasteSaveRequestDto requestDto) {

        TastePutResponseDto tastePutResponseDto = tasteService.putTaste(user, requestDto);
        return new ResponseEntity<>(tastePutResponseDto, HttpStatus.OK);
    }

    @DeleteMapping()
    public ResponseEntity<TasteDeleteResponseDto> deleteTaste(@AuthenticationPrincipal User user) {

        TasteDeleteResponseDto tasteDeleteResponseDto = tasteService.deleteTaste(user);
        return new ResponseEntity<>(tasteDeleteResponseDto, HttpStatus.OK);
    }
}
