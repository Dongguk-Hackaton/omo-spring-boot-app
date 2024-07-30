package org.omo.omospringboot.entity.travel;

import jakarta.persistence.*;
import lombok.*;
import org.omo.omospringboot.entity.user.User;

@Entity
@Table
@Getter
@NoArgsConstructor(access = AccessLevel.PROTECTED)
@AllArgsConstructor
@Builder
public class UserTravel {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    @ManyToOne(fetch = FetchType.LAZY)
    User user;

    @ManyToOne(fetch = FetchType.LAZY)
    Travel travel;

    public static UserTravel of(User user, Travel newTravel) {
        return UserTravel.builder()
                .user(user)
                .travel(newTravel)
                .build();
    }
}
