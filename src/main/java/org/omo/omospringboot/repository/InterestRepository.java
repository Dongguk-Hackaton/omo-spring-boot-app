package org.omo.omospringboot.repository;

import org.omo.omospringboot.entity.Interest;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.Optional;

public interface InterestRepository extends JpaRepository<Interest, Long> {
    Optional<Interest> findByInterestCode(String interestCode);
}
