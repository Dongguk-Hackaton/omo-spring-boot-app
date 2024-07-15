package org.omo.omospringboot.entity;

import jakarta.persistence.*;
import lombok.*;

import java.util.List;

@Entity
@Table
@Getter
@Setter
@NoArgsConstructor(access = AccessLevel.PROTECTED)
@AllArgsConstructor
@Builder
public class TasteProfile {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    @OneToOne
    private User user;

    @Column(nullable = false)
    private int userActivity;

    @Column(nullable = false)
    private int userWalking;

    @ManyToMany
    private List<Interest> interests;

    public static TasteProfile of(User user, int userActivity, int userWalking, List<Interest> interests) {
        return TasteProfile.builder()
                .user(user)
                .userActivity(userActivity)
                .userWalking(userWalking)
                .interests(interests)
                .build();
    }

    public void update(User user, int userActivity, int userWalking, List<Interest> interests) {
        this.user = user;
        this.userActivity = userActivity;
        this.userWalking = userWalking;
        this.interests = interests;
    }
}
