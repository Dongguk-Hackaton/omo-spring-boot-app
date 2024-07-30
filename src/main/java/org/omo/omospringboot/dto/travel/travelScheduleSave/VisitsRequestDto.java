package org.omo.omospringboot.dto.travel.travelScheduleSave;

import com.fasterxml.jackson.databind.PropertyNamingStrategies;
import com.fasterxml.jackson.databind.annotation.JsonNaming;
import jakarta.validation.constraints.NotNull;
import lombok.Getter;
import lombok.Setter;

import java.time.LocalDateTime;

@Getter
@Setter
@JsonNaming(value = PropertyNamingStrategies.SnakeCaseStrategy.class)
public class VisitsRequestDto {
    @NotNull(message = "방문장소에 머무는 시작 시간이 필요합니다.")
    private LocalDateTime startTime;

    @NotNull(message = "방문장소에 머무는 종료 시간이 필요합니다.")
    private LocalDateTime endTime;

    @NotNull(message = "타임블럭이 필요합니다.")
    private String timeBlockType;

    @NotNull(message = "장소에 대한 아이디가 필요합니다.")
    private Long placeId;
}
