package org.omo.omospringboot.controller.travel;

import jakarta.validation.Valid;
import lombok.RequiredArgsConstructor;
import org.omo.omospringboot.dto.travel.NearestSchedule;
import org.omo.omospringboot.dto.travel.TravelScheduleSave;
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

    @GetMapping("/nearest")
    public ResponseEntity<NearestSchedule.Response> getNearestSchedule(@AuthenticationPrincipal User user) {
        return new ResponseEntity<>(travelScheduleService.getNearestSchedule(user), HttpStatus.OK);

    }


    @PostMapping()
    public ResponseEntity<TravelScheduleSave.Response> saveTravelCourse(
            @AuthenticationPrincipal User user,
            @Valid @RequestBody TravelScheduleSave.Request requestDto
    ) {
        travelScheduleService.saveTravelSchedule(user, requestDto);
        return new ResponseEntity<>(new TravelScheduleSave.Response(), HttpStatus.CREATED);
    }
}
