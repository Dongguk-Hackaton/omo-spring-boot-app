package org.omo.omospringboot.repository.travel;

import org.omo.omospringboot.entity.travel.Travel;
import org.omo.omospringboot.entity.user.User;
import org.springframework.data.domain.Pageable;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;

import java.util.List;

public interface TravelRepository extends JpaRepository<Travel, Long> {
    @Query("SELECT t " +
            "FROM Travel t " +
            "WHERE t.startDay >= CURRENT_DATE AND t.id IN " +
            "(SELECT ut.travel.id " +
            "FROM UserTravel ut " +
            "WHERE ut.user = :user)" +
            "ORDER BY t.startDay ASC")
    List<Travel> findTopByUserOrderByStartDayAsc(@Param("user") User user, Pageable pageable);

}

