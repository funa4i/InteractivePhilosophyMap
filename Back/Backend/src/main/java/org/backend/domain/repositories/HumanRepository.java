package org.backend.domain.repositories;

import org.backend.domain.entity.Human;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.List;
import java.util.Set;

public interface HumanRepository extends JpaRepository<Human, Long> {
    List<Human> findByBornYearBetweenOrDieYearBetween(Integer startB, Integer endB, Integer startD, Integer endD);
}
