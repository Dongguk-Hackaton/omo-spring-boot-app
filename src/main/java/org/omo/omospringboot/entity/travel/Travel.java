package org.omo.omospringboot.entity.travel;

import jakarta.persistence.*;
import lombok.*;
import org.omo.omospringboot.constant.CityType;
import org.omo.omospringboot.constant.ProvinceType;
import org.omo.omospringboot.dto.travel.travelScheduleSave.TravelScheduleSaveRequestDto;

import java.time.LocalDate;

@Entity
@Table
@Getter
@NoArgsConstructor(access = AccessLevel.PROTECTED)
@AllArgsConstructor
@Builder
public class Travel {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    @Column(nullable = false)
    private LocalDate startDay;

    @Column(nullable = false)
    private LocalDate endDay;

    @Column(nullable = false)
    private ProvinceType province; // 도/광역시

    @Column(nullable = false)
    private CityType city; // 시/군

    public static Travel of(TravelScheduleSaveRequestDto requestDto, ProvinceType province, CityType city) {
        return Travel.builder()
                .startDay(requestDto.getStartDay())
                .endDay(requestDto.getEndDay())
                .province(province)
                .city(city)
                .build();
    }
}
