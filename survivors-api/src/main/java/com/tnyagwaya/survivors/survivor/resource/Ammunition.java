package com.tnyagwaya.survivors.survivor.resource;

import lombok.Data;

import javax.persistence.DiscriminatorValue;
import javax.persistence.Entity;

@Entity
@DiscriminatorValue("Ammunition")
@Data
public class Ammunition extends Resource {
    private int size;
    private AmmunitionType type;
}
