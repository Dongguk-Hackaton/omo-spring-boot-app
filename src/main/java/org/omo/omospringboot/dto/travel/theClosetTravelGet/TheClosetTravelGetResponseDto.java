package org.omo.omospringboot.dto.travel.theClosetTravelGet;

import lombok.*;
import org.omo.omospringboot.constant.CityType;
import org.omo.omospringboot.constant.ProvinceType;
import org.omo.omospringboot.entity.travel.Travel;

import java.time.LocalDate;

@Getter
@Setter
@NoArgsConstructor
@AllArgsConstructor
@Builder
public class TheClosetTravelGetResponseDto {
    private Long id;

    private LocalDate startDay;

    private LocalDate endDay;

    private ProvinceType province; // 도/광역시

    private CityType city; // 시/군

    public static TheClosetTravelGetResponseDto fromDto(Travel travel){
        return TheClosetTravelGetResponseDto.builder()
                .id(travel.getId())
                .startDay(travel.getStartDay())
                .endDay(travel.getEndDay())
                .province(travel.getProvince())
                .city(travel.getCity())
                .build();
    }
}
