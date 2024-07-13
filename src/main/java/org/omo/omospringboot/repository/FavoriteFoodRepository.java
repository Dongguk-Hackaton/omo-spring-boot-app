package org.omo.omospringboot.repository;

import org.omo.omospringboot.entity.FavoriteFood;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.List;

public interface FavoriteFoodRepository extends JpaRepository<FavoriteFood, Long> {
    List<FavoriteFood> findByTasteProfileId(Long id);

    void deleteByTasteProfileId(Long id);
}
