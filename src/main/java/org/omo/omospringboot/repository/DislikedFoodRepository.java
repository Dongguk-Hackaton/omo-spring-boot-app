package org.omo.omospringboot.repository;

import org.omo.omospringboot.entity.DislikedFood;
import org.springframework.data.jpa.repository.JpaRepository;

public interface DislikedFoodRepository extends JpaRepository<DislikedFood, Long> {
}
