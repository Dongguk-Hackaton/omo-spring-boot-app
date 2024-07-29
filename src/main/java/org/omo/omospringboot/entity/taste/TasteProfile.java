package org.omo.omospringboot.entity.taste;

import jakarta.persistence.*;
import lombok.*;
import org.omo.omospringboot.entity.user.User;

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

    public static TasteProfile of(User user, int userActivity) {
        return TasteProfile.builder()
                .user(user)
                .userActivity(userActivity)
                .build();
    }

    public void update(User user, int userActivity) {
        this.user = user;
        this.userActivity = userActivity;
    }
}
