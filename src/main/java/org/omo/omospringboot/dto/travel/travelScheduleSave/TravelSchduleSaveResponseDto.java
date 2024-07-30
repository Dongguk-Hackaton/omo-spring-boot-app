package org.omo.omospringboot.dto.travel.travelScheduleSave;

import com.fasterxml.jackson.databind.PropertyNamingStrategies;
import com.fasterxml.jackson.databind.annotation.JsonNaming;
import lombok.Getter;
import lombok.Setter;

@Getter
@Setter

@JsonNaming(value = PropertyNamingStrategies.SnakeCaseStrategy.class)
public class TravelSchduleSaveResponseDto {
    private String message;

    public TravelSchduleSaveResponseDto() {
        this.message = "여행 일정이 등록되었습니다.";
    }
}
