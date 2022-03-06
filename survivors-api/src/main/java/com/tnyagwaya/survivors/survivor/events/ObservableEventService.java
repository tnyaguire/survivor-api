package com.tnyagwaya.survivors.survivor.events;

public interface ObservableEventService {
    void registerObserver(SurvivorObserver observer, Class<? extends AbstractObservableEvent> eventClass);

    void notifyObservers(AbstractObservableEvent event);
}
