package org.omo.omospringboot.dto.travel;

import com.fasterxml.jackson.databind.PropertyNamingStrategies;
import com.fasterxml.jackson.databind.annotation.JsonNaming;
import lombok.*;
import org.omo.omospringboot.constant.CityType;
import org.omo.omospringboot.constant.ProvinceType;
import org.omo.omospringboot.entity.travel.Travel;

import java.time.LocalDate;

public class NearestSchedule {
    @Getter
    @AllArgsConstructor
    @Builder
    @JsonNaming(value = PropertyNamingStrategies.SnakeCaseStrategy.class)
    public static class Response {
        private Long id;

        private LocalDate startDay;

        private LocalDate endDay;

        private ProvinceType province; // 도/광역시

        private CityType city; // 시/군

        public static Response fromDto(Travel travel) {
            return Response.builder()
                    .id(travel.getId())
                    .startDay(travel.getStartDay())
                    .endDay(travel.getEndDay())
                    .province(travel.getProvince())
                    .city(travel.getCity())
                    .build();
        }
    }
}
