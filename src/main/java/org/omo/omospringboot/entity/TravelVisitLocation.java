package org.omo.omospringboot.entity;

import jakarta.persistence.*;
import lombok.*;
import org.omo.omospringboot.constant.TimeBlockType;

import java.time.LocalDate;
import java.time.LocalDateTime;
import java.time.LocalTime;

@Entity
@Table
@Getter
@NoArgsConstructor(access = AccessLevel.PROTECTED)
@AllArgsConstructor
@Builder
public class TravelVisitLocation {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    @ManyToOne(fetch = FetchType.LAZY)
    private TravelSchedule travelSchedule;

    @Column(nullable = false)
    private LocalDateTime startTime; // 장소에 머무는 시작 시간

    @Column(nullable = false)
    private LocalDateTime endTime; // 장소에 머무는 종료 시간

    @Column(nullable = false)
    private TimeBlockType timeBlockType;

    @OneToOne
    private Location location;
}
