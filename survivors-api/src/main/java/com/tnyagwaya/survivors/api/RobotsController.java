package com.tnyagwaya.survivors.api;

import com.tnyagwaya.survivors.data.ErrorObject;
import com.tnyagwaya.survivors.robot.RobotCategory;
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
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

import static org.springframework.util.MimeTypeUtils.APPLICATION_JSON_VALUE;

@RestController
@Slf4j
@Tag(name = "ROBOTS",
        description="The **ROBOTS** APIs are used to fetch a list of all robots and their known locations.")
@RequestMapping(path = {"/api/v1/robots"}, produces = APPLICATION_JSON_VALUE)
public class RobotsController {

    @Autowired
    private RobotService robotService;

    @Operation(summary = "List of robots", description = "")
    @ApiResponses(value = {
            @ApiResponse(responseCode = "200", description = "Represents a list of robots",
                    content = {@Content(mediaType = APPLICATION_JSON_VALUE,
                    schema = @Schema(implementation = RobotsInfo.class))}),
            @ApiResponse(responseCode = "400", description = "Represents an Error Caused by the Violation of a Business Rule",
                    content = {@Content(mediaType = "application/json",
                            schema = @Schema(implementation = ErrorObject.class))})
    })
    @GetMapping
    public ResponseEntity<?> getRobots(@RequestParam(defaultValue="All") RobotCategory robotCategory){
        return ResponseEntity.status(HttpStatus.OK).body(robotService.getRobots(robotCategory));
    }
}
