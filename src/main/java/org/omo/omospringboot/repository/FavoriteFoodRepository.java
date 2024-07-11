package org.omo.omospringboot.repository;

import org.omo.omospringboot.entity.FavoriteFood;
import org.springframework.data.jpa.repository.JpaRepository;

public interface FavoriteFoodRepository extends JpaRepository<FavoriteFood, Long> {
}
