package com.tnyagwaya.survivors.data;

import io.swagger.v3.oas.annotations.media.Schema;
import lombok.Data;

import java.io.Serializable;
import java.time.Instant;

@Data
@Schema(description = "A representation of an error.")
public class ErrorObject implements Serializable {
    @Schema(description = "Error code based of HTTP Status.")
    private String code;
    @Schema(description = "Status reason.")
    private String description;
    @Schema(description = "Server time (Epoch millis) the error was raised.")
    private long timestamp = Instant.now().getEpochSecond();

    public ErrorObject(String errorCode, String errorDescription) {
        this.code = errorCode;
        this.description = errorDescription;
    }
}
