package org.omo.omospringboot.dto.taste;

import lombok.Builder;
import lombok.Getter;
import lombok.Setter;

import java.time.LocalDateTime;

@Getter
@Setter
@Builder
public class TastePutResponseDto {
    private String message;

    private LocalDateTime updateTime;

    private Long tasteProfileId;
}
