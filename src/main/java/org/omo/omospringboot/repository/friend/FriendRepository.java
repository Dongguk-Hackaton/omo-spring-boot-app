package org.omo.omospringboot.repository.friend;

import org.omo.omospringboot.entity.user.FriendShip;
import org.omo.omospringboot.entity.user.User;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import java.util.List;
import java.util.Optional;

@Repository
public interface FriendRepository extends JpaRepository<FriendShip, Long> {
    boolean existsByUserAndFriend(User user, User friend);

    List<FriendShip> findAllByUser(User user);

    Optional<FriendShip> findByUserAndFriend(User user, User friend);
}
