package com.tnyagwaya.survivors.data;

import com.fasterxml.jackson.annotation.JsonFormat;
import com.fasterxml.jackson.annotation.JsonProperty;
import com.tnyagwaya.survivors.survivor.Gender;
import com.tnyagwaya.survivors.survivor.Location;
import com.tnyagwaya.survivors.survivor.Survivor;
import io.swagger.v3.oas.annotations.media.Schema;
import lombok.AllArgsConstructor;
import lombok.Builder;
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
    @Schema(description = "Survivor firstname.")
    private String name;

    @NotEmpty
    @NotBlank
    @JsonProperty(value="lastName", required = true)
    @Schema(description = "Survivor lastname.")
    private String lastName;

    @JsonProperty(value="gender", required = true)
    @Schema(description = "Survivor gender.")
    private Gender gender;

    @NotEmpty
    @NotBlank
    @JsonProperty(value = "survivorId", required = true)
    @Schema(description = "SurvivorId.")
    private String survivorId;

    @NotNull
    @JsonProperty(value = "location", required = true)
    @Schema(description = "Survivor last known location.")
    private Location location;

    @JsonProperty(value = "dob", required = true)
    @JsonFormat(pattern="dd-MM-yyyy")
    @Past
    @Schema(description = "Survivor date of birth.")
    private LocalDate dob;

    @JsonProperty("resources")
    private List<ResourceInfo> resources = Collections.emptyList();

    @NotEmpty
    @NotBlank
    @JsonProperty(value = "reportedBy", required = true)
    @Schema(description = "Survivor date of birth.")
    private String createdBy;

}
