package com.tnyagwaya.survivors.survivor;

import com.tnyagwaya.survivors.data.FlagInfectionRequest;
import com.tnyagwaya.survivors.data.SurvivorInfo;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;

import java.util.List;
import java.util.Optional;

public interface SurvivorService {

    Survivor addSurvivor(SurvivorInfo survivorInfo);

    Location updateLocation(final Location location, final String survivorId);

    FlagInfectionRequest flagInfection(final FlagInfectionRequest flagInfectionRequest);

    Optional<Survivor> findByNationalId(final String nationalId);

    Page<SurvivorInfo> findByInfected(boolean infected, Pageable pageable);

    List<SurvivorSummary> generateReportSummary();
}
