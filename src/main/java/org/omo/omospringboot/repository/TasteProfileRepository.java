package org.omo.omospringboot.repository;

import org.omo.omospringboot.entity.TasteProfile;
import org.omo.omospringboot.entity.User;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.Optional;

public interface TasteProfileRepository extends JpaRepository<TasteProfile, Long> {
    Optional<TasteProfile> findByUser(User user);
}
