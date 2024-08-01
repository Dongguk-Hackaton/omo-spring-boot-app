package org.omo.omospringboot.dto.friend;

import com.fasterxml.jackson.databind.PropertyNamingStrategies;
import com.fasterxml.jackson.databind.annotation.JsonNaming;
import lombok.Builder;
import lombok.Getter;
import lombok.Setter;
import org.omo.omospringboot.constant.DateStyleType;
import org.omo.omospringboot.constant.FoodType;
import org.omo.omospringboot.constant.InterestType;

import java.util.List;

@Getter
@Setter
@Builder
@JsonNaming(value = PropertyNamingStrategies.SnakeCaseStrategy.class)
public class FriendDetailGetResponseDto {

    private Long friendId;

    private String friendNickname;

    private String friendProfileImage;

    private int friendActivity;

    private List<DateStyleType> friendDateStyles;

    private List<FoodType> friendFavoriteFoods;

    private List<FoodType> friendDislikedFoods;

    private List<InterestType> friendInterests;
}
