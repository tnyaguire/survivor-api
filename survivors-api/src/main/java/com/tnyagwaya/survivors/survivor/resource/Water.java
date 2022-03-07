package com.tnyagwaya.survivors.survivor.resource;

import lombok.Data;

import javax.persistence.DiscriminatorValue;
import javax.persistence.Entity;
import javax.persistence.EnumType;
import javax.persistence.Enumerated;


@Entity
@Data
@DiscriminatorValue("Water")
public class Water extends Resource {

    @Enumerated(EnumType.STRING)
    private WaterType type;
}
