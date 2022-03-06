package com.tnyagwaya.survivors.survivor.events;

public interface SurvivorObserver <T extends AbstractObservableEvent>{

     void update(T event);

    Class<? extends AbstractObservableEvent> getEventClass();
}
