package com.tnyagwaya.survivors.survivor.resource;

import lombok.Data;

import javax.persistence.DiscriminatorValue;
import javax.persistence.Entity;

@Data
@Entity
@DiscriminatorValue("Medication")
public class Medication extends Resource {
    private String name;
    private String description;
    private String dosageForm;
}
