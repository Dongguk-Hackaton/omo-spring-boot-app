package org.omo.omospringboot.entity;

import jakarta.persistence.*;
import lombok.*;
import org.omo.omospringboot.constant.DateStyleType;

@Entity
@Table
@Getter
@NoArgsConstructor(access = AccessLevel.PROTECTED)
@AllArgsConstructor
@Builder
public class DateStyle {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    @ManyToOne
    private TasteProfile tasteProfile;

    @Column(nullable = false)
    private DateStyleType dateStyleType;

    public static DateStyle of(TasteProfile tasteProfile, DateStyleType dateStyleType){
        return DateStyle.builder()
                .tasteProfile(tasteProfile)
                .dateStyleType(dateStyleType)
                .build();
    }
}
