package com.tnyagwaya.survivors.data;

import com.fasterxml.jackson.annotation.JsonProperty;
import com.tnyagwaya.survivors.survivor.resource.ResourceAttributes;
import com.tnyagwaya.survivors.survivor.resource.ResourceType;
import com.tnyagwaya.survivors.survivor.resource.Unit;
import lombok.Data;

import java.io.Serializable;
import java.math.BigDecimal;

@Data
public class ResourceInfo implements Serializable {

    @JsonProperty("description")
    private String description;

    @JsonProperty("resourceType")
    private ResourceType resourceType;

    @JsonProperty("unit")
    private Unit unit;

    @JsonProperty("quantity")
    private BigDecimal quantity;

    @JsonProperty("attributes")
    private ResourceAttributes attributes = new ResourceAttributes();
}
