package com.tnyagwaya.survivors.survivor;

import com.tnyagwaya.survivors.data.FlagInfectionRequest;
import com.tnyagwaya.survivors.data.SurvivorInfo;
import common.AbstractTestUtilities;
import lombok.extern.slf4j.Slf4j;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageRequest;
import org.springframework.test.annotation.Rollback;
import org.springframework.transaction.annotation.Transactional;

import java.math.BigDecimal;
import java.util.List;
import java.util.Optional;

import static org.assertj.core.api.AssertionsForClassTypes.assertThat;
import static org.assertj.core.api.AssertionsForClassTypes.offset;
import static org.assertj.core.api.AssertionsForClassTypes.within;

@SpringBootTest
@Transactional
@Slf4j
class SurvivorServiceImplIntegrationTest extends AbstractTestUtilities {

    @Autowired
    private SurvivorService survivorService;

    @Test
    void shouldAddASurvivorIfInfoIsValid() {
        final SurvivorInfo info = getSurvivorInfo();
        final Survivor survivor = survivorService.addSurvivor(info);
        assertThat(survivor).isNotNull();
        assertThat(survivor.getSurvivorId()).isNotNull();
        assertThat(survivor.getInfectionFlagCount()).isEqualTo(0);
    }

    @Test
    void shouldUpdateLocation() {

        final SurvivorInfo info = getSurvivorInfo();

        final Survivor survivor = survivorService.addSurvivor(info);

        assertThat(survivor.getLastLocation().getLatitude()).isEqualTo(new BigDecimal(-17.82772));

        final Location location = new Location(new BigDecimal(-17.0000), new BigDecimal(31.0000));

        survivorService.updateLocation(location,survivor.getSurvivorId());

        final Optional<Survivor> optional = survivorService.findBySurvivorId(info.getSurvivorId());

        assertThat(optional.isPresent());

        final Survivor updatedSurvivor = optional.get();

        assertThat(updatedSurvivor.getLastLocation().getLatitude()).isEqualTo(new BigDecimal(-17.0000));

        assertThat(updatedSurvivor.getLastLocation().getLongitude()).isEqualTo(new BigDecimal(31.0000));
    }

    @Test
    void shouldFlagInfectedSurvivorOnlyOnceIfReportedBySameSurvivor() {

        final SurvivorInfo info = getSurvivorInfo();

        final Survivor survivor = survivorService.addSurvivor(info);

        survivorService.flagInfection(new FlagInfectionRequest(survivor.getSurvivorId(),"bug-reporter"));

        Survivor flaggedSuvivor = survivorService.findBySurvivorId(info.getSurvivorId()).orElseThrow();

        assertThat(flaggedSuvivor.getInfectionFlagCount()).isEqualTo(1);

        survivorService.flagInfection(new FlagInfectionRequest(survivor.getSurvivorId(),"bug-reporter"));

        assertThat(flaggedSuvivor.getInfectionFlagCount()).isEqualTo(1);

    }


    @Test
    void shouldFlagInfectedSurvivorIfReportedBy3OtherSurvivors() {

        final SurvivorInfo info = getSurvivorInfo();

        final Survivor survivor = survivorService.addSurvivor(info);

        survivorService.flagInfection(new FlagInfectionRequest(survivor.getSurvivorId(),"bug-reporter-1"));

        Survivor flaggedSuvivor = survivorService.findBySurvivorId(info.getSurvivorId()).orElseThrow();

        assertThat(flaggedSuvivor.getInfectionFlagCount()).isEqualTo(1);

        survivorService.flagInfection(new FlagInfectionRequest(survivor.getSurvivorId(),"bug-reporter-2"));

        assertThat(flaggedSuvivor.getInfectionFlagCount()).isEqualTo(2);

        survivorService.flagInfection(new FlagInfectionRequest(survivor.getSurvivorId(),"bug-reporter-3"));

        assertThat(flaggedSuvivor.getInfectionFlagCount()).isEqualTo(3);

        assertThat(flaggedSuvivor.isInfected());

    }

    @Test
    void shouldReturnNonInfectedSurvivors() {
        final SurvivorInfo info = getSurvivorInfo();
        survivorService.addSurvivor(info);
        final Page<SurvivorInfo> byInfected = survivorService.findByInfected(false, PageRequest.of(0, 1));
        assertThat(byInfected.getTotalElements()).isGreaterThan(0);
    }

    @Test
    void shouldReturnInfectedSurvivors() {
        final SurvivorInfo info = getSurvivorInfo();
        final Survivor survivor = survivorService.addSurvivor(info);
        survivorService.flagInfection(new FlagInfectionRequest(survivor.getSurvivorId(),"bug-reporter-1"));
        survivorService.flagInfection(new FlagInfectionRequest(survivor.getSurvivorId(),"bug-reporter-2"));
        survivorService.flagInfection(new FlagInfectionRequest(survivor.getSurvivorId(),"bug-reporter-3"));
        final Page<SurvivorInfo> byInfected = survivorService.findByInfected(true, PageRequest.of(0, 1));
        assertThat(byInfected.getTotalElements()).isGreaterThan(0);
    }
}