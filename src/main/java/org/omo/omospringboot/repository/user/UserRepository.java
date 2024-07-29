package org.omo.omospringboot.repository.user;

import org.omo.omospringboot.entity.user.User;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.Optional;

public interface UserRepository extends JpaRepository<User, Long> {
    Optional<User> findByUserProfileId(Long userProfileId);
}
