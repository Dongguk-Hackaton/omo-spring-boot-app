package org.omo.omospringboot.dto.taste;

import com.fasterxml.jackson.databind.PropertyNamingStrategies;
import com.fasterxml.jackson.databind.annotation.JsonNaming;
import lombok.Builder;
import lombok.Getter;
import lombok.Setter;

import java.time.LocalDateTime;

@Getter
@Setter
@Builder
@JsonNaming(value = PropertyNamingStrategies.SnakeCaseStrategy.class)
public class TasteDeleteResponseDto {
    private String message;

    private LocalDateTime deletedTime;

    private Long tasteProfileId;
}
