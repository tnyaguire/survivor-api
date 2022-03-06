package com.tnyagwaya.survivors.data;

import com.fasterxml.jackson.annotation.JsonProperty;
import lombok.Data;

import java.io.Serializable;

@Data
public class FlagInfectionResponse implements Serializable {

    private String survivorId;

    @JsonProperty(value = "flagCount")
    private int flagCount;



}
