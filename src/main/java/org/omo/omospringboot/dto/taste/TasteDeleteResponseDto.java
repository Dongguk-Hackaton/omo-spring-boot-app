package org.omo.omospringboot.dto.taste;

import lombok.Getter;
import lombok.Setter;

@Getter
@Setter
public class TasteDeleteResponseDto {
    private String message;

    public TasteDeleteResponseDto(){
        this.message = "취향이 삭제되었습니다.";
    }
}
