package com.tnyagwaya.survivors.survivor;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import java.util.Optional;

@Repository
public interface SurvivorRepository extends JpaRepository<Survivor,Long> {
    Optional<Survivor> findBySurvivorIdIgnoreCase(final String nationalId);
}
