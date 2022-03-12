package com.tnyagwaya.survivors.survivor.resource;

import lombok.Data;

import javax.persistence.DiscriminatorValue;
import javax.persistence.Entity;
import javax.persistence.EnumType;
import javax.persistence.Enumerated;

@Entity
@DiscriminatorValue("AMMUNITION")
@Data
public class Ammunition extends Resource {

    private int size;

    @Enumerated(EnumType.STRING)
    private AmmunitionType type;
}
