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
@DiscriminatorValue("RESTAURANT")
public class Restaurant extends Location{
    @Column(nullable = false)
    private String firstMenu;

    @Column(nullable = false)
    private String subMenu;

    private String reservation;

    @Column(nullable = false)
    private String parking;
}
