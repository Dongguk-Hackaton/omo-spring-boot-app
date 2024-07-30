package org.omo.omospringboot.entity.travel;

import jakarta.persistence.*;
import lombok.*;
import org.omo.omospringboot.dto.travel.TravelScheduleSave;

import java.time.LocalDateTime;

@Entity
@Table
@Getter
@NoArgsConstructor(access = AccessLevel.PROTECTED)
@AllArgsConstructor
@Builder
public class ItineraryDays {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    @ManyToOne(fetch = FetchType.LAZY)
    private Travel travel;

    @Column(nullable = false)
    private LocalDateTime startTime; // 여행 시작시간

    @Column(nullable = false)
    private LocalDateTime endTime; // 여행 종료시간


    public static ItineraryDays of(Travel newTravel, TravelScheduleSave.Itinerary schedule) {
        return ItineraryDays.builder()
                .travel(newTravel)
                .startTime(schedule.getStartTime())
                .endTime(schedule.getEndTime())
                .build();
    }
}
