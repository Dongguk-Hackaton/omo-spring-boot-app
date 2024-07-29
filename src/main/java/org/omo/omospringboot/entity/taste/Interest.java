package org.omo.omospringboot.entity.taste;

import jakarta.persistence.*;
import lombok.*;
import org.omo.omospringboot.constant.InterestType;

@Entity
@Table
@Getter
@NoArgsConstructor(access = AccessLevel.PROTECTED)
@AllArgsConstructor
@Builder
public class Interest {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    @ManyToOne(fetch = FetchType.LAZY)
    private TasteProfile tasteProfile;

    @Column(nullable = false)
    private InterestType interestType;

    public static Interest of(TasteProfile tasteProfile, InterestType interestType){
        return Interest.builder()
                .tasteProfile(tasteProfile)
                .interestType(interestType)
                .build();
    }
}
