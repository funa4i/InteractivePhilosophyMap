package org.backend.domain.repositories;

import org.backend.domain.entity.Museum;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.List;

public interface MuseumRepository extends JpaRepository<Museum, Long> {
    List<Museum> findByOpenYearBetween(Integer start, Integer end);
}
