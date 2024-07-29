package org.omo.omospringboot.repository;

import org.omo.omospringboot.entity.DateStyle;
import org.omo.omospringboot.entity.TasteProfile;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.List;

public interface DateStyleRepository extends JpaRepository<DateStyle, Long> {

    List<DateStyle> findByTasteProfile(TasteProfile tasteProfile);

    void deleteByTasteProfile(TasteProfile tasteProfile);
}
