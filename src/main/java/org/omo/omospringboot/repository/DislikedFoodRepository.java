package org.omo.omospringboot.repository;

import org.omo.omospringboot.entity.DislikedFood;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.List;

public interface DislikedFoodRepository extends JpaRepository<DislikedFood, Long> {
    List<DislikedFood> findByTasteProfileId(Long id);

    void deleteByTasteProfileId(Long id);
}
