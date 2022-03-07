package com.tnyagwaya.survivors.survivor;

import com.tnyagwaya.survivors.data.FlagInfectionRequest;
import com.tnyagwaya.survivors.data.ResourceInfo;
import com.tnyagwaya.survivors.data.SurvivorInfo;
import com.tnyagwaya.survivors.survivor.events.ObservableEventService;
import com.tnyagwaya.survivors.survivor.events.SurvivorInfectedEvent;
import com.tnyagwaya.survivors.survivor.resource.Resource;
import com.tnyagwaya.survivors.survivor.resource.ResourceFactory;
import com.tnyagwaya.survivors.survivor.resource.ResourceRepository;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.NoSuchElementException;
import java.util.Optional;
import java.util.stream.Collectors;

@RequiredArgsConstructor
@Slf4j
@Service
public class SurvivorServiceImpl implements SurvivorService {

    private final List<ResourceFactory> resourceFactories;
    private final SurvivorRepository survivorRepository;
    private final ResourceRepository resourceRepository;
    private final InfectedSurvivorFlagRepository infectedSurvivorFlagRepository;
    private final ObservableEventService observableEventService;

    @Override
    public Survivor addSurvivor(final SurvivorInfo survivorInfo) {
        log.info("adding survivor: {}", survivorInfo);
        final Survivor survivor = generateSurvivor(survivorInfo);
        final Survivor savedSurvivor = survivorRepository.save(survivor);
        final List<Resource> resources = survivorInfo.getResources()
                .stream().map(r -> createResource(r, savedSurvivor, survivorInfo.getCreatedBy())).collect(Collectors.toList());
        resourceRepository.saveAll(resources);
        return savedSurvivor;
    }

    public Location updateLocation(final Location location, final String survivorId) {
        log.info("updating location");
        final Survivor survivor = survivorRepository.findBySurvivorIdIgnoreCase(survivorId)
                .orElseThrow(() -> new NoSuchElementException("Survivor for id not found."));
        survivor.setLastLocation(location);
        survivorRepository.save(survivor);
        return location;
    }

    @Override
    public FlagInfectionRequest flagInfection(FlagInfectionRequest flagRequest) {

        final Survivor survivor = survivorRepository.findBySurvivorIdIgnoreCase(flagRequest.getSurvivorId())
                .orElseThrow(() -> new NoSuchElementException("Survivor for id not found."));

        final Optional<InfectedSurvivorFlag> flagOptional = infectedSurvivorFlagRepository.findBySurvivorAndReportedByIgnoringCase(survivor, flagRequest.getReportedBy());

        if(flagOptional.isPresent()){
            log.info("Similar report already exist");
            return flagRequest;
        }

        final InfectedSurvivorFlag flag = new InfectedSurvivorFlag(survivor, flagRequest.getReportedBy());

        infectedSurvivorFlagRepository.save(flag);

        final SurvivorInfectedEvent survivorInfectedEvent = new SurvivorInfectedEvent(flagRequest.getReportedBy(), flagRequest.getSurvivorId());

        observableEventService.notifyObservers(survivorInfectedEvent);

        return flagRequest;
    }

    @Override
    public Optional<Survivor> findByNationalId(String nationalId) {
        return survivorRepository.findBySurvivorIdIgnoreCase(nationalId);
    }

    private Resource createResource(final ResourceInfo resourceInfo, final Survivor survivor, final String createdBy){
        final ResourceFactory factory = resourceFactories.stream().filter(r -> r.supports(resourceInfo))
                .findAny()
                .orElseThrow();
        return  factory.create(resourceInfo, survivor, createdBy);
    }

    private Survivor generateSurvivor(final SurvivorInfo info){
        final Survivor survivor = new Survivor();
        survivor.setLastName(info.getLastName());
        survivor.setLastLocation(info.getLocation());
        survivor.setDob(info.getDob());
        survivor.setSurvivorId(info.getSurvivorId());
        survivor.setCreatedBy(info.getCreatedBy());
        return survivor;
    }

}
