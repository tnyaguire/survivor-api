package com.tnyagwaya.survivors.survivor.events;

import com.tnyagwaya.survivors.robot.ConfigProperties;
import com.tnyagwaya.survivors.survivor.Survivor;
import com.tnyagwaya.survivors.survivor.SurvivorRepository;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.stereotype.Component;

import javax.annotation.PostConstruct;
import java.util.Optional;

@Component
@Slf4j
@RequiredArgsConstructor
public class SurvivorInfectedEventListener implements SurvivorObserver<SurvivorInfectedEvent> {

    private final SurvivorRepository survivorRepository;

    private final ConfigProperties properties;

    private final ObservableEventService observableEventService;

    @PostConstruct
    public void init(){
        observableEventService.registerObserver(this,SurvivorInfectedEvent.class);
    }

    @Override
    public void update(SurvivorInfectedEvent event) {
        log.info("handling SurvivorInfectedEvent: {}", event);
        final Optional<Survivor> optional = survivorRepository.findBySurvivorIdIgnoreCase(event.getSurvivorId());
        if (!optional.isPresent()) {
            log.info("No survivor with id found");
            return;
        }
        final Survivor survivor = optional.get();
        final int infectionFlagCount = survivor.getInfectionFlagCount() + 1;
        survivor.setInfectionFlagCount(infectionFlagCount);
        if (infectionFlagCount >= properties.getMinFlags()) {
            log.info("Flagging survivor with ID - {} as infected.", event.getSurvivorId());
            survivor.setInfected(true);
        }
        survivorRepository.save(survivor);
    }

    @Override
    public Class<? extends AbstractObservableEvent> getEventClass() {
        return SurvivorInfectedEvent.class;
    }

}
