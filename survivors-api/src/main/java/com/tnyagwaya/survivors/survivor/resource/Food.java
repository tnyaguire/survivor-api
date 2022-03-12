package com.tnyagwaya.survivors.survivor.resource;

import lombok.Data;

import javax.persistence.DiscriminatorValue;
import javax.persistence.Entity;
import java.math.BigDecimal;

@Data
@DiscriminatorValue("FOOD")
@Entity
public class Food extends Resource{
    private BigDecimal calories;
    private boolean halaal = false;
    private BigDecimal proteinContent = BigDecimal.ZERO;
}
