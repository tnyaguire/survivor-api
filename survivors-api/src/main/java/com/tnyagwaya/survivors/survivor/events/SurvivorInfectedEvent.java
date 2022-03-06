package com.tnyagwaya.survivors.survivor.events;

import lombok.Data;

@Data
public class SurvivorInfectedEvent extends AbstractObservableEvent {
    private String reportedBy;
    private String survivorId;

    public SurvivorInfectedEvent(String reportedBy, String survivorId) {
        this.reportedBy = reportedBy;
        this.survivorId = survivorId;
    }
}
