package com.tnyagwaya.survivors.data;

import io.swagger.v3.oas.annotations.media.Schema;
import lombok.Data;

import javax.validation.constraints.NotBlank;
import javax.validation.constraints.NotEmpty;
import java.io.Serializable;

@Data
public class FlagInfectionRequest implements Serializable {
    @NotBlank
    @NotEmpty
    @Schema(description = "The Id of the infected survivor.")
    private String survivorId;

    @NotBlank
    @NotEmpty
    @Schema(description = "ID of the survivor reporting the infection.")
    private String reportedBy;
}
