package com.tnyagwaya.survivors.api;

import com.tnyagwaya.survivors.data.ErrorObject;
import com.tnyagwaya.survivors.data.SurvivorInfo;
import com.tnyagwaya.survivors.robot.RobotService;
import com.tnyagwaya.survivors.robot.RobotsInfo;
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
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import javax.validation.Valid;

import static org.springframework.util.MimeTypeUtils.APPLICATION_JSON_VALUE;

@RestController
@Slf4j
@Tag(name = "REPORTS",
        description="The **REPORTS** APIs are used to report on survivors and robots.")
@RequestMapping(path = {"/api/v1/reports"}, produces = APPLICATION_JSON_VALUE)
public class ReportingController {

    @Autowired
    private RobotService survivorService;

    @Operation(summary = "List of robots", description = "")
    @ApiResponses(value = {
            @ApiResponse(responseCode = "200", description = "Represents a list of robots",
                    content = {@Content(mediaType = APPLICATION_JSON_VALUE,
                    schema = @Schema(implementation = SurvivorInfo.class))}),
            @ApiResponse(responseCode = "400", description = "Represents an Error Caused by the Violation of a Business Rule",
                    content = {@Content(mediaType = "application/json",
                            schema = @Schema(implementation = ErrorObject.class))})
    })
    @PostMapping
    public ResponseEntity<?> register(@Valid @RequestBody SurvivorInfo survivorInfo){

        log.info("Registering survivor: {}", survivorInfo);
       return ResponseEntity.ok().build();
    }

}
