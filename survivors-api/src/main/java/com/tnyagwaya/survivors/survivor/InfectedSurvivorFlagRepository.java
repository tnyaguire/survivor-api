package com.tnyagwaya.survivors.survivor;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import java.util.Optional;

@Repository
public interface InfectedSurvivorFlagRepository extends JpaRepository<InfectedSurvivorFlag,Long> {
    Optional<InfectedSurvivorFlag> findBySurvivorAndReportedByIgnoringCase(final Survivor survivor, final String reportedBy);
}
