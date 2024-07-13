package org.omo.omospringboot.dto.taste;

import lombok.Getter;
import lombok.Setter;

@Getter
@Setter
public class TastePatchResponseDto {
    private String message;

    public TastePatchResponseDto(){
        this.message = "취향이 수정되었습니다.";
    }
}
