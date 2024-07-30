package org.omo.omospringboot.dto.travel.travelScheduleSave;

import com.fasterxml.jackson.databind.PropertyNamingStrategies;
import com.fasterxml.jackson.databind.annotation.JsonNaming;
import jakarta.validation.Valid;
import jakarta.validation.constraints.NotNull;
import lombok.Getter;
import lombok.Setter;

import java.time.LocalDateTime;
import java.util.List;

@Getter
@Setter
@JsonNaming(value = PropertyNamingStrategies.SnakeCaseStrategy.class)
// 하루 동안의 여행 일정
public class TravelItineraryRequestDto {
    @NotNull(message = "여행 시작 시간이 필요합니다.")
    private LocalDateTime startTime;

    @NotNull(message = "여행 종료 시간이 필요합니다.")
    private LocalDateTime endTime;

    @Valid
    @NotNull(message = "방문장소가 하나 이상 필요합니다.")
    List<VisitsRequestDto> visits;
}
