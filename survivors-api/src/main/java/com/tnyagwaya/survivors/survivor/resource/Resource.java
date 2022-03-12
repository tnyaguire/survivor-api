package com.tnyagwaya.survivors.survivor.resource;

import com.tnyagwaya.survivors.common.BaseEntity;
import com.tnyagwaya.survivors.survivor.Survivor;
import lombok.Data;

import javax.persistence.Column;
import javax.persistence.DiscriminatorColumn;
import javax.persistence.DiscriminatorType;
import javax.persistence.Entity;
import javax.persistence.EnumType;
import javax.persistence.Enumerated;
import javax.persistence.Inheritance;
import javax.persistence.InheritanceType;
import javax.persistence.ManyToOne;
import java.math.BigDecimal;

@Data
@Entity(name="resource")
@Inheritance(strategy = InheritanceType.SINGLE_TABLE)
@DiscriminatorColumn(name="resource_category",
        discriminatorType = DiscriminatorType.STRING)
public  class Resource extends BaseEntity {

    private BigDecimal quantity;

    private Unit unit;

    private String description;

    @Column(name = "resource_category", insertable = false, updatable = false)
    @Enumerated(EnumType.STRING)
    private ResourceType resourceCategory;

    @ManyToOne
    private Survivor survivor;
}
