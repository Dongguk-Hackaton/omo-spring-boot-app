package org.omo.omospringboot.repository;

import org.omo.omospringboot.entity.DislikedFood;
import org.omo.omospringboot.entity.TasteProfile;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.List;

public interface DislikedFoodRepository extends JpaRepository<DislikedFood, Long> {

    List<DislikedFood> findByTasteProfile(TasteProfile tasteProfile);

    void deleteByTasteProfile(TasteProfile tasteProfile);
}
