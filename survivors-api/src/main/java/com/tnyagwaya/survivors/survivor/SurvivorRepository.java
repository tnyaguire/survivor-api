package com.tnyagwaya.survivors.survivor;

import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.stereotype.Repository;

import java.util.List;
import java.util.Optional;

@Repository
public interface SurvivorRepository extends JpaRepository<Survivor,Long> {

    Optional<Survivor> findBySurvivorIdIgnoreCase(final String nationalId);

    Page<Survivor> findByInfected(boolean infected, Pageable pageable);

    @Query(value = "select case when infected=0 then 'NOT_INFECTED' else 'INFECTED' end as status, count(*) as count, count(*) * 100.0 / sum(count(*)) over() as percentage, sum(count(*)) over() as totalSurvivors FROM survivor group by infected", nativeQuery = true)
    List<Stats> generateReportSummary();
}
