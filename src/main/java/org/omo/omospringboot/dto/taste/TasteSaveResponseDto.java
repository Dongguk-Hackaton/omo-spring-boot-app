package org.omo.omospringboot.dto.taste;

import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Getter;
import lombok.Setter;

@Getter
@Setter
public class TasteSaveResponseDto {
    private String message;

    public TasteSaveResponseDto(){
        this.message = "취향이 등록되었습니다.";
    }
}
