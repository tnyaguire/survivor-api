package com.tnyagwaya.survivors.api;

import com.tnyagwaya.survivors.data.ErrorObject;
import com.tnyagwaya.survivors.data.FlagInfectionRequest;
import com.tnyagwaya.survivors.data.SurvivorInfo;
import com.tnyagwaya.survivors.survivor.Location;
import com.tnyagwaya.survivors.survivor.Survivor;
import com.tnyagwaya.survivors.survivor.SurvivorService;
import io.swagger.v3.oas.annotations.Operation;
import io.swagger.v3.oas.annotations.media.Content;
import io.swagger.v3.oas.annotations.media.Schema;
import io.swagger.v3.oas.annotations.responses.ApiResponse;
import io.swagger.v3.oas.annotations.responses.ApiResponses;
import io.swagger.v3.oas.annotations.tags.Tag;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import javax.validation.Valid;

import static org.springframework.util.MimeTypeUtils.APPLICATION_JSON_VALUE;

@RestController
@Slf4j
@Tag(name = "SURVIVORS",
        description="The **SURVIVORS** APIs are used to store information about the survivors, report infections as well as update the last known location.")
@RequestMapping(path = {"/api/v1/survivors"}, produces = APPLICATION_JSON_VALUE)
public class SurvivorController {

    @Autowired
    private SurvivorService survivorService;

    @Operation(summary = "Add survivors to the database", description = "Provided with a valid object representation, this " +
            "endpoint allows for a new survivor to be added")
    @ApiResponses(value = {
            @ApiResponse(responseCode = "201", description = "Represents a SurvivorInfo response",
                    content = {@Content(mediaType = APPLICATION_JSON_VALUE,
                    schema = @Schema(implementation = SurvivorInfo.class))}),
            @ApiResponse(responseCode = "400", description = "Represents an Error Caused by the Violation of a Business Rule",
                    content = {@Content(mediaType = "application/json",
                            schema = @Schema(implementation = ErrorObject.class))})
    })
    @PostMapping
    public ResponseEntity<?> register(@Valid @RequestBody SurvivorInfo survivorInfo){
        log.info("Registering survivor: {}", survivorInfo);
        final Survivor survivor = survivorService.addSurvivor(survivorInfo);
        return ResponseEntity.status(HttpStatus.CREATED).body(survivor);
    }

    @Operation(summary = "Update survivor location", description = "Provided with a valid object representation, this " +
            "endpoint allows for a survivor to update their last location.")
    @ApiResponses(value = {
            @ApiResponse(responseCode = "202", description = "Represents a Location response",
                    content = {@Content(mediaType = APPLICATION_JSON_VALUE,
                            schema = @Schema(implementation = SurvivorInfo.class))}),
            @ApiResponse(responseCode = "400", description = "Represents an Error Caused by the Violation of a Business Rule",
                    content = {@Content(mediaType = "application/json",
                            schema = @Schema(implementation = ErrorObject.class))})
    })
    @PutMapping("/{nationalId}/location")
    public ResponseEntity<?> updateLocation(@Valid @RequestBody final Location location, @PathVariable String nationalId) {
        log.info("updating location");
        survivorService.updateLocation(location,nationalId);
        return ResponseEntity.status(HttpStatus.ACCEPTED).body(location);
    }


    @Operation(summary = "Flag survivor as infected", description = "Provided with a valid object representation, this " +
            "endpoint allows for a survivor to be flagged as infected. A survivor is marked as infected when at least three other survivors report their\n" +
            "contamination.")
    @ApiResponses(value = {
            @ApiResponse(responseCode = "202", description = "Represents a Location response",
                    content = {@Content(mediaType = APPLICATION_JSON_VALUE,
                            schema = @Schema(implementation = SurvivorInfo.class))}),
            @ApiResponse(responseCode = "400", description = "Represents an Error Caused by the Violation of a Business Rule",
                    content = {@Content(mediaType = "application/json",
                            schema = @Schema(implementation = ErrorObject.class))})
    })
    @PostMapping("/flag")
    public ResponseEntity<?> flagAsInfected(@Valid @RequestBody final FlagInfectionRequest flagRequest) {
        log.info("Flagging survivor: {}", flagRequest);
        survivorService.flagInfection(flagRequest);
        return ResponseEntity.status(HttpStatus.ACCEPTED).body(flagRequest);
    }

}
