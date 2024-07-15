package org.omo.omospringboot.dto.taste;

import lombok.Builder;
import lombok.Getter;
import lombok.Setter;

import java.time.LocalDateTime;

@Getter
@Setter
@Builder
public class TasteDeleteResponseDto {
    private String message;

    private LocalDateTime deleteTime;

    private Long tasteProfileId;
}
