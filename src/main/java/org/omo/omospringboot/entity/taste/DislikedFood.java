package org.omo.omospringboot.entity.taste;

import jakarta.persistence.*;
import lombok.*;
import org.omo.omospringboot.constant.FoodType;

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
    private FoodType foodType;

    public static DislikedFood of(TasteProfile tasteProfile, FoodType foodType){
        return DislikedFood.builder()
                .tasteProfile(tasteProfile)
                .foodType(foodType)
                .build();
    }
}
