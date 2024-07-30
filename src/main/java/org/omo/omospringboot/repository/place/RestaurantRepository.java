package org.omo.omospringboot.repository.place;

import org.omo.omospringboot.entity.place.Restaurant;
import org.springframework.data.jpa.repository.JpaRepository;

public interface RestaurantRepository extends JpaRepository<Restaurant, Long> {

}

