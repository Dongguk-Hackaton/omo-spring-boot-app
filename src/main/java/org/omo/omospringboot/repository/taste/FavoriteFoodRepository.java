package org.omo.omospringboot.repository.taste;

import org.omo.omospringboot.entity.taste.FavoriteFood;
import org.omo.omospringboot.entity.taste.TasteProfile;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.List;

public interface FavoriteFoodRepository extends JpaRepository<FavoriteFood, Long> {

    List<FavoriteFood> findByTasteProfile(TasteProfile tasteProfile);

    void deleteByTasteProfile(TasteProfile tasteProfile);
}
