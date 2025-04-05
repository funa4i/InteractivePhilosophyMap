package org.backend.domain.repositories;

import org.backend.domain.entity.Direction;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.List;

public interface DirectionRepository extends JpaRepository<Direction, Long> {
    List<Direction> findByBornYearBetween(Integer start, Integer end);
}
