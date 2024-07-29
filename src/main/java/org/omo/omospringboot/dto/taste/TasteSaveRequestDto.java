package org.omo.omospringboot.dto.taste;

import com.fasterxml.jackson.databind.PropertyNamingStrategies;
import com.fasterxml.jackson.databind.annotation.JsonNaming;
import jakarta.validation.constraints.Max;
import jakarta.validation.constraints.Min;
import lombok.Getter;
import lombok.Setter;

import java.util.List;

@Getter
@Setter
@JsonNaming(value = PropertyNamingStrategies.SnakeCaseStrategy.class)
public class TasteSaveRequestDto {
    @Min(value = 0, message = "활동량은 0이상이여야 합니다.")
    @Max(value = 10, message = "활동량은 10이하이여야 합니다.")
    private int userActivity;

    private List<String> dateStyles;

    private List<String> favoriteFoods;

    private List<String> dislikedFoods;

    private List<String> interests;
}
