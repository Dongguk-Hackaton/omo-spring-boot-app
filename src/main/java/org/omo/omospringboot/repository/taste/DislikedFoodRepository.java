package org.omo.omospringboot.repository.taste;

import org.omo.omospringboot.entity.taste.DislikedFood;
import org.omo.omospringboot.entity.taste.TasteProfile;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.List;

public interface DislikedFoodRepository extends JpaRepository<DislikedFood, Long> {

    List<DislikedFood> findByTasteProfile(TasteProfile tasteProfile);

    void deleteByTasteProfile(TasteProfile tasteProfile);
}
