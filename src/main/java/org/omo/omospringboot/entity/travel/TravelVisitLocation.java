package org.omo.omospringboot.entity.travel;

import jakarta.persistence.*;
import lombok.*;
import org.omo.omospringboot.constant.TimeBlockType;
import org.omo.omospringboot.entity.location.Location;

import java.time.LocalDateTime;

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
