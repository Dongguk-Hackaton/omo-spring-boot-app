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
@DiscriminatorValue("restaurant")
public class Restaurant extends Place {
    @Column(nullable = false)
    private String firstMenu;

    @Column(nullable = false)
    private String subMenu;

    private String reservation;

    @Column(nullable = false)
    private String parking;
}
