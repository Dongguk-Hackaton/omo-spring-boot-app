package org.omo.omospringboot.repository.taste;

import org.omo.omospringboot.entity.taste.DateStyle;
import org.omo.omospringboot.entity.taste.TasteProfile;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.List;

public interface DateStyleRepository extends JpaRepository<DateStyle, Long> {

    List<DateStyle> findByTasteProfile(TasteProfile tasteProfile);

    void deleteByTasteProfile(TasteProfile tasteProfile);
}
