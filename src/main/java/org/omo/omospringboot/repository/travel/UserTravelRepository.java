package org.omo.omospringboot.repository.travel;

import org.omo.omospringboot.entity.travel.UserTravel;
import org.omo.omospringboot.entity.user.User;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.List;

public interface UserTravelRepository extends JpaRepository<UserTravel, Long> {
}

