package com.tnyagwaya.survivors.robot;

import com.fasterxml.jackson.annotation.JsonProperty;
import lombok.Data;

import java.io.Serializable;

@Data
public class Robot implements Serializable {
    @JsonProperty("model")
    private String model;
    @JsonProperty("serialNumber")
    private String serialNumber;
    @JsonProperty("manufacturedDate")
    private String manufacturedDate;
    @JsonProperty("category")
    private RobotCategory category;
}
