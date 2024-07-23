package org.omo.omospringboot.entity;

import jakarta.persistence.*;
import lombok.*;
import org.omo.omospringboot.constant.FoodType;

@Entity
@Table
@Getter
@NoArgsConstructor(access = AccessLevel.PROTECTED)
@AllArgsConstructor
@Builder
public class FavoriteFood {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    @ManyToOne
    private TasteProfile tasteProfile;

    @Column(nullable = false)
    private FoodType foodType;

    public static FavoriteFood of(TasteProfile tasteProfile, FoodType foodType){
        return FavoriteFood.builder()
                .tasteProfile(tasteProfile)
                .foodType(foodType)
                .build();
    }
}
