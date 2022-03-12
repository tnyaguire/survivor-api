package com.tnyagwaya.survivors.survivor;

import io.swagger.v3.oas.annotations.media.Schema;

import java.math.BigDecimal;

@Schema(description = "A summary of survivor information for a given status.")
public interface SurvivorSummary {

    @Schema(description = "Number of survivors for the given status.")
    BigDecimal getCount();

    @Schema(description = "Percentage of survivors with status")
    BigDecimal getPercentage();

    @Schema(description = "Total number of survivors. Includes both infected and non-infected survivors.")
    BigDecimal getTotalSurvivors();

    @Schema(description = "Infection status")
    InfectionStatus getStatus();
}
