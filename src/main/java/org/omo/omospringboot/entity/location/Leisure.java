package org.omo.omospringboot.entity.location;

import jakarta.persistence.Column;
import jakarta.persistence.DiscriminatorValue;
import jakarta.persistence.Entity;
import jakarta.persistence.Table;
import lombok.*;

@Entity
@Table
@Getter
@NoArgsConstructor(access = AccessLevel.PROTECTED)
@AllArgsConstructor
@Builder
@DiscriminatorValue("LEISURE")
public class Leisure extends Location{
    private String duration; // 개장기간

    private String fee;

    private String pet;

    private String detailInformation;

    @Column(nullable = false)
    private String parking;
}
