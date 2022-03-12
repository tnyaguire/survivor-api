package com.tnyagwaya.survivors.api;

import com.tnyagwaya.survivors.data.ErrorObject;
import com.tnyagwaya.survivors.data.SurvivorInfo;
import com.tnyagwaya.survivors.survivor.SurvivorService;
import com.tnyagwaya.survivors.survivor.SurvivorSummary;
import io.swagger.v3.oas.annotations.Operation;
import io.swagger.v3.oas.annotations.media.Content;
import io.swagger.v3.oas.annotations.media.Schema;
import io.swagger.v3.oas.annotations.responses.ApiResponse;
import io.swagger.v3.oas.annotations.responses.ApiResponses;
import io.swagger.v3.oas.annotations.tags.Tag;
import lombok.extern.slf4j.Slf4j;

import org.springframework.beans.factory.annotation.Autowired;

import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.web.bind.annotation.GetMapping;

import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

import java.util.List;

import static org.springframework.util.MimeTypeUtils.APPLICATION_JSON_VALUE;

@RestController
@Slf4j
@Tag(name = "REPORTS",
        description="The **REPORTS** APIs are used to report on survivors and robots.")
@RequestMapping(path = {"/api/v1/reports"}, produces = APPLICATION_JSON_VALUE)
public class ReportingController {

    @Autowired
    private SurvivorService survivorService;

    @Operation(summary = "List survivors by Infection status", description = "")
    @ApiResponses(value = {
            @ApiResponse(responseCode = "200", description = "Represents a Page of survivors"),
            @ApiResponse(responseCode = "400", description = "Represents an Error Caused by the Violation of a Business Rule",
                    content = {@Content(mediaType = "application/json",
                            schema = @Schema(implementation = ErrorObject.class))})
    })
    @GetMapping("/survivors")
    public Page<SurvivorInfo> getSurvivors(@RequestParam(required = false,
            defaultValue = "false") boolean infected, Pageable pageable){
        log.info("Listing survivors: Infected = {}", infected);
       return survivorService.findByInfected(infected, pageable);
    }

    @Operation(summary = "Survivor Statistics", description = "Statistics on survivors and their infection status, including count and percentages")
    @ApiResponses(value = {
            @ApiResponse(responseCode = "200", description = "Represents survivor stats"),
            @ApiResponse(responseCode = "400", description = "Represents an Error Caused by the Violation of a Business Rule")
    })
    @GetMapping("/survivors/stats")
    public List<SurvivorSummary> getSurvivorSummary(){
        log.info("Survivors summary");
       return survivorService.generateReportSummary();
    }
}
