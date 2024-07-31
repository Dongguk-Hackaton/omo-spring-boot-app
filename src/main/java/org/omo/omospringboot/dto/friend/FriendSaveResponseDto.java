package org.omo.omospringboot.dto.friend;


import lombok.Getter;
import lombok.Setter;

@Getter
@Setter
public class FriendSaveResponseDto {
    private String message;

    public FriendSaveResponseDto() {
        this.message = "친구가 등록되었습니다.";
    }
}
