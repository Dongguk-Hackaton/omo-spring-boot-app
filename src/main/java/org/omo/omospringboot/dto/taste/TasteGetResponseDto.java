package org.omo.omospringboot.dto.taste;

import com.fasterxml.jackson.databind.PropertyNamingStrategies;
import com.fasterxml.jackson.databind.annotation.JsonNaming;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Getter;
import lombok.Setter;

import java.util.List;

@Getter
@Setter
@JsonNaming(value = PropertyNamingStrategies.SnakeCaseStrategy.class)
@Builder
public class TasteGetResponseDto {

    private int userActivity;

    private int userWalking;

    private List<String> favoriteFoods;

    private List<String> dislikedFoods;

    private List<String> interestCode;
}

