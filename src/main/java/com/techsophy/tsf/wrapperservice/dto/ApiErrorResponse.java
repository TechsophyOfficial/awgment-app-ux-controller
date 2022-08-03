package com.techsophy.tsf.wrapperservice.dto;

import com.fasterxml.jackson.annotation.JsonFormat;
import lombok.Value;
import org.springframework.http.HttpStatus;
import java.time.Instant;

/**
 * api error response
 */
@Value
public class ApiErrorResponse
{
    @JsonFormat(shape = JsonFormat.Shape.STRING, pattern = "yyyy-MM-dd'T'HH:mm:ss.SSS'Z'", timezone = "UTC")
    Instant timestamp;
    String message;
    String error;
    HttpStatus status;
    String path;
}
