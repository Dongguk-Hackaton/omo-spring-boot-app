package org.omo.omospringboot.repository.taste;

import org.omo.omospringboot.entity.taste.TasteProfile;
import org.omo.omospringboot.entity.user.User;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.Optional;

public interface TasteProfileRepository extends JpaRepository<TasteProfile, Long> {
    Optional<TasteProfile> findByUser(User user);

    TasteProfile findByUserId(Long userId);
}
