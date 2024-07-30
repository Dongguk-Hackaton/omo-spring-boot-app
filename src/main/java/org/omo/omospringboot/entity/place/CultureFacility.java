package org.omo.omospringboot.entity.place;

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
@DiscriminatorValue("culture_facility")
public class CultureFacility extends Place {
    private String fee;

    private String discountInfo;

    private String pet;

    private String detailInformation; // 상세 정보

    @Column(nullable = false)
    private String parking;
}
