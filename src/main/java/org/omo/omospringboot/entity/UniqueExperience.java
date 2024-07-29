package org.omo.omospringboot.entity;

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
@DiscriminatorValue("UNIQUE_EXPERIENCE")
public class UniqueExperience extends Location{
    private String type;
}
