package org.omo.omospringboot.repository;

import org.omo.omospringboot.constant.InterestType;
import org.omo.omospringboot.entity.Interest;
import org.omo.omospringboot.entity.TasteProfile;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.List;
import java.util.Optional;

public interface InterestRepository extends JpaRepository<Interest, Long> {
    Optional<Interest> findByInterestType(InterestType interestType);

    List<Interest> findByTasteProfile(TasteProfile tasteProfile);

    void deleteByTasteProfile(TasteProfile tasteProfile);
}
