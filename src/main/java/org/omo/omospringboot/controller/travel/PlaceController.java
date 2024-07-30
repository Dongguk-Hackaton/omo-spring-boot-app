package org.omo.omospringboot.controller.travel;

import lombok.RequiredArgsConstructor;
import org.omo.omospringboot.dto.place.PlaceGet;
import org.omo.omospringboot.entity.place.Place;
import org.omo.omospringboot.service.travel.PlaceService;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

@RestController
@RequiredArgsConstructor
@RequestMapping("/api/place")
public class PlaceController {
    private final PlaceService placeService;

    @GetMapping("/{id}")
    public ResponseEntity<PlaceGet.Response> getPlace(@PathVariable(name = "id") Long placeId) {
        PlaceGet.Response placeResponseDto = placeService.getPlace(placeId);
        return new ResponseEntity<>(placeResponseDto, HttpStatus.OK);
    }
}
