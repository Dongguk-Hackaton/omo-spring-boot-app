package org.omo.omospringboot.entity.travel;

import jakarta.persistence.*;
import lombok.*;
import org.omo.omospringboot.constant.TimeBlockType;
import org.omo.omospringboot.dto.travel.TravelScheduleSave;
import org.omo.omospringboot.entity.place.Place;

import java.time.LocalDateTime;

@Entity
@Table
@Getter
@NoArgsConstructor(access = AccessLevel.PROTECTED)
@AllArgsConstructor
@Builder
public class Visits {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    @ManyToOne(fetch = FetchType.LAZY)
    private ItineraryDays travelSchedule;

    @Column(nullable = false)
    private LocalDateTime startTime; // 장소에 머무는 시작 시간

    @Column(nullable = false)
    private LocalDateTime endTime; // 장소에 머무는 종료 시간

    @Column(nullable = false)
    private TimeBlockType timeBlockType;

    @ManyToOne(fetch = FetchType.LAZY)
    private Place place;

    public static Visits of(ItineraryDays newTravelSchedule, TimeBlockType timeBlockType, Place place, TravelScheduleSave.Visits requestDto) {
        return Visits.builder()
                .travelSchedule(newTravelSchedule)
                .startTime(requestDto.getStartTime())
                .endTime(requestDto.getEndTime())
                .timeBlockType(timeBlockType)
                .place(place)
                .build();
    }
}
