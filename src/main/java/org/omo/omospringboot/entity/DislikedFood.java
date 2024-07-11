package org.omo.omospringboot.entity;

import jakarta.persistence.*;
import lombok.*;

@Entity
@Table
@Getter
@NoArgsConstructor(access = AccessLevel.PROTECTED)
@AllArgsConstructor
@Builder
public class DislikedFood {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    @ManyToOne
    private TasteProfile tasteProfile;

    @Column(nullable = false)
    private String foodName;

    public static DislikedFood of(TasteProfile tasteProfile, String foodName){
        return DislikedFood.builder()
                .tasteProfile(tasteProfile)
                .foodName(foodName)
                .build();
    }
}
