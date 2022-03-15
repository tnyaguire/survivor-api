package com.tnyagwaya.survivors.survivor.events;

import lombok.extern.slf4j.Slf4j;

import org.springframework.stereotype.Service;
import org.springframework.util.CollectionUtils;

import java.util.HashMap;
import java.util.HashSet;
import java.util.Map;
import java.util.Set;

@Slf4j
@Service
public class ObservableEventServiceImpl implements ObservableEventService {

    private final Map<Class<? extends AbstractObservableEvent>, Set<SurvivorObserver>> eventListeners = new HashMap<>();

    @Override
    public void registerObserver(SurvivorObserver observer, Class<? extends AbstractObservableEvent> eventClass){
        eventListeners.computeIfAbsent(eventClass, k-> new HashSet<>()).add(observer);
        log.info("Add listener {} for event {} ", observer.getClass().getSimpleName(), eventClass.getSimpleName());
    }

    @Override
    public void notifyObservers(AbstractObservableEvent event){
        final Set<SurvivorObserver> listeners = eventListeners.get(event.getClass());
        if(CollectionUtils.isEmpty(listeners)){
            log.info("No listeners found for: {}", event.getClass());
            return;
        }
        log.info("Processing {} listeners for : {}", listeners.size(), event.getClass());
        listeners.stream().forEach(listener -> listener.update(event));
    }

}
