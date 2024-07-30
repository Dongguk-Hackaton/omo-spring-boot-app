package org.omo.omospringboot.dto.travel.travelScheduleSave;

import com.fasterxml.jackson.databind.PropertyNamingStrategies;
import com.fasterxml.jackson.databind.annotation.JsonNaming;
import jakarta.validation.Valid;
import jakarta.validation.constraints.NotNull;
import lombok.Getter;
import lombok.Setter;

import java.time.LocalDate;
import java.util.List;

@Getter
@Setter
@JsonNaming(value = PropertyNamingStrategies.SnakeCaseStrategy.class)
public class TravelScheduleSaveRequestDto {
    @NotNull(message = "여행 시작일이 필요합니다.")
    private LocalDate startDay; // 여행 시작일

    @NotNull(message = "여행 종료일이 필요합니다.")
    private LocalDate endDay; // 여행 종료일

    @NotNull(message = "여행할 도/광역시가 필요합니다.")
    private String province;

    @NotNull(message = "여행할 시/군이 필요합니다.")
    private String city;

    private Long friendId;

    @Valid
    @NotNull(message = "여행 일정이 하루 이상 필요합니다.")
    List<TravelItineraryRequestDto> itineraries;
}
