package org.omo.omospringboot.dto.friend;

import com.fasterxml.jackson.databind.PropertyNamingStrategies;
import com.fasterxml.jackson.databind.annotation.JsonNaming;
import lombok.Builder;
import lombok.Getter;
import lombok.Setter;

@Getter
@Setter
@Builder
@JsonNaming(value = PropertyNamingStrategies.SnakeCaseStrategy.class)
public class FriendGetResponseDto {

   private Long friendId;

   private String friendNickname;

   private String friendProfileImage;
}
