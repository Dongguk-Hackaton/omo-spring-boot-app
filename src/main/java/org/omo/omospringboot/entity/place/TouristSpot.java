package org.omo.omospringboot.entity.place;

import jakarta.persistence.Column;
import jakarta.persistence.DiscriminatorValue;
import jakarta.persistence.Entity;
import jakarta.persistence.Table;
import lombok.*;

import java.time.LocalDateTime;

@Entity
@Table
@Getter
@NoArgsConstructor(access = AccessLevel.PROTECTED)
@AllArgsConstructor
@Builder
@DiscriminatorValue("tourist_spot")
public class TouristSpot extends Place {
    private LocalDateTime startDay;

    private String experienceInformation;

    private String pet;

    private String detailInformation;

    @Column(nullable = false)
    private String parking;
}
