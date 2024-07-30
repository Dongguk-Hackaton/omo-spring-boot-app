package org.omo.omospringboot.controller.travel;

import jakarta.validation.Valid;
import lombok.RequiredArgsConstructor;
import org.omo.omospringboot.dto.travel.theClosetTravelGet.TheClosetTravelGetResponseDto;
import org.omo.omospringboot.dto.travel.travelScheduleSave.TravelScheduleSaveRequestDto;
import org.omo.omospringboot.dto.travel.travelScheduleSave.TravelSchduleSaveResponseDto;
import org.omo.omospringboot.entity.user.User;
import org.omo.omospringboot.service.travel.TravelScheduleService;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.security.core.annotation.AuthenticationPrincipal;
import org.springframework.web.bind.annotation.*;

@RestController
@RequiredArgsConstructor
@RequestMapping("/api/travel/schedule")
public class TravelScheduleController {
    private final TravelScheduleService travelScheduleService;

    @GetMapping("/closet")
    public ResponseEntity<TheClosetTravelGetResponseDto> getTheClosetTravelSchedule(@AuthenticationPrincipal User user) {
        return new ResponseEntity<>(travelScheduleService.getTheClosetTravelSchedule(user), HttpStatus.OK);

    }


    @PostMapping()
    public ResponseEntity<TravelSchduleSaveResponseDto> saveTravelCourse(
            @AuthenticationPrincipal User user,
            @Valid @RequestBody TravelScheduleSaveRequestDto requestDto
    ) {
        travelScheduleService.saveTravelSchedule(user, requestDto);
        return new ResponseEntity<>(new TravelSchduleSaveResponseDto(), HttpStatus.CREATED);
    }
}
