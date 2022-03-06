package com.tnyagwaya.survivors.survivor;

import com.tnyagwaya.survivors.common.BaseEntity;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.JoinColumn;
import javax.persistence.ManyToOne;
import javax.persistence.Table;

@Entity
@Table(name = "infected_survivor_flag")
public class InfectedSurvivorFlag extends BaseEntity {

    @ManyToOne
    private Survivor survivor;

    @Column(name = "reported_by")
    private String reportedBy;

    public InfectedSurvivorFlag() {
    }

    public InfectedSurvivorFlag(Survivor survivor, String reportedBy) {
        this.survivor = survivor;
        this.reportedBy = reportedBy;
    }
}
