package org.omo.omospringboot.dto.taste;

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
@JsonNaming(value = PropertyNamingStrategies.SnakeCaseStrategy.class)
@Builder
public class TasteGetResponseDto {

    private int userActivity;

    private List<DateStyleType> dateStyles;

    private List<FoodType> favoriteFoods;

    private List<FoodType> dislikedFoods;

    private List<InterestType> interests;
}

