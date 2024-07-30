package org.omo.omospringboot.repository.travel;

import org.omo.omospringboot.entity.travel.ItineraryDays;
import org.springframework.data.jpa.repository.JpaRepository;

public interface ItineraryRepository extends JpaRepository<ItineraryDays, Long> {

}

