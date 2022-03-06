package com.tnyagwaya.survivors.data;

import com.fasterxml.jackson.annotation.JsonFormat;
import com.fasterxml.jackson.annotation.JsonProperty;
import com.tnyagwaya.survivors.survivor.Location;
import lombok.Data;

import javax.validation.constraints.NotBlank;
import javax.validation.constraints.NotEmpty;
import javax.validation.constraints.NotNull;
import javax.validation.constraints.Past;
import java.io.Serializable;
import java.time.LocalDate;
import java.util.Collections;
import java.util.List;

@Data
public class SurvivorInfo implements Serializable {

    @JsonProperty(value = "name", required = true)
    @NotEmpty
    @NotBlank
    private String name;

    @NotEmpty
    @NotBlank
    @JsonProperty(value="lastName", required = true)
    private String lastName;

    @NotEmpty
    @NotBlank
    @JsonProperty(value = "survivorId", required = true)
    private String survivorId;

    @NotNull
    @JsonProperty(value = "location", required = true)
    private Location location;

    @JsonProperty(value = "dob", required = true)
    @JsonFormat(pattern="dd-MM-yyyy")
    @Past
    private LocalDate dob;

    @JsonProperty("resources")
    private List<ResourceInfo> resources = Collections.emptyList();

    @NotEmpty
    @NotBlank
    @JsonProperty(value = "reportedBy", required = true)
    private String createdBy;
}
