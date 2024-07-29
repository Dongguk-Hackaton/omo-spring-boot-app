package org.omo.omospringboot.entity.travel;

import jakarta.persistence.*;
import lombok.*;

import java.time.LocalDateTime;

@Entity
@Table
@Getter
@NoArgsConstructor(access = AccessLevel.PROTECTED)
@AllArgsConstructor
@Builder
public class TravelSchedule {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    @ManyToOne(fetch = FetchType.LAZY)
    private Travel travel;

    @Column(nullable = false)
    private LocalDateTime startTime; // 여행 시작시간

    @Column(nullable = false)
    private LocalDateTime endTime; // 여행 종료시간
}
