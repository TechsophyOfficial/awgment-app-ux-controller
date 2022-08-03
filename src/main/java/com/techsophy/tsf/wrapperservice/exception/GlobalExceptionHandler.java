package com.techsophy.tsf.wrapperservice.exception;

import com.techsophy.tsf.wrapperservice.dto.ApiErrorResponse;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.ControllerAdvice;
import org.springframework.web.bind.annotation.ExceptionHandler;
import org.springframework.web.client.RestClientResponseException;
import org.springframework.web.context.request.WebRequest;
import org.springframework.web.servlet.mvc.method.annotation.ResponseEntityExceptionHandler;
import java.time.Instant;
import static org.springframework.http.HttpStatus.INTERNAL_SERVER_ERROR;

/**
 * Global Exception handler
 */
@ControllerAdvice
public class GlobalExceptionHandler extends ResponseEntityExceptionHandler
{

    /**
     * Global exception
     * @param ex
     * @param request
     * @return ResponseEntity
     */
    @ExceptionHandler(Exception.class)
    public ResponseEntity<ApiErrorResponse> handleGlobalException(Exception ex, WebRequest request)
    {
        ApiErrorResponse errorDetails = new ApiErrorResponse(Instant.now(), ex.getMessage(), ex.getMessage(),
                HttpStatus.INTERNAL_SERVER_ERROR, request.getDescription(false));
        return new ResponseEntity<>(errorDetails, HttpStatus.INTERNAL_SERVER_ERROR);
    }

    /**
     * rest client response exception
     * @param ex
     * @param request
     * @return ResponseEntity
     */
    @ExceptionHandler(RestClientResponseException.class)
    public ResponseEntity<ApiErrorResponse> handleRestClientResponseException(RestClientResponseException ex, WebRequest request)
    {
        ApiErrorResponse errorDetails = new ApiErrorResponse(Instant.now(), ex.getMessage(), ex.getMessage(),
                HttpStatus.INTERNAL_SERVER_ERROR, request.getDescription(false));
        return new ResponseEntity<>(errorDetails, INTERNAL_SERVER_ERROR);
    }

    /**
     * Missing mandatory data exception
     * @param ex
     * @param request
     * @return ResponseEntity
     */
    @ExceptionHandler(MissingMandatoryDataException.class)
    public ResponseEntity<ApiErrorResponse> handleMissingMandatoryDataException(MissingMandatoryDataException ex, WebRequest request)
    {
        ApiErrorResponse errorDetails = new ApiErrorResponse(Instant.now(), ex.getMessage(), ex.getMessage(),
                HttpStatus.INTERNAL_SERVER_ERROR, request.getDescription(false));
        return new ResponseEntity<>(errorDetails, HttpStatus.INTERNAL_SERVER_ERROR);
    }

    @ExceptionHandler(ResumeProcessException.class)
    public ResponseEntity<ApiErrorResponse> handleResumeProcessException(ResumeProcessException ex, WebRequest request)
    {
        ApiErrorResponse errorDetails = new ApiErrorResponse(Instant.now(), ex.getMessage(), ex.getMessage(),
                HttpStatus.INTERNAL_SERVER_ERROR, request.getDescription(false));
        return new ResponseEntity<>(errorDetails, HttpStatus.INTERNAL_SERVER_ERROR);
    }

    @ExceptionHandler(ServerErrorException.class)
    public ResponseEntity<ApiErrorResponse> handleCreateProcessException(ServerErrorException ex, WebRequest request)
    {
        ApiErrorResponse errorDetails = new ApiErrorResponse(Instant.now(), ex.getMessage(), ex.getMessage(),
                HttpStatus.INTERNAL_SERVER_ERROR, request.getDescription(false));
        return new ResponseEntity<>(errorDetails, HttpStatus.INTERNAL_SERVER_ERROR);
    }
    @ExceptionHandler(UpdateTaskException.class)
    public ResponseEntity<ApiErrorResponse> handleUpdateTaskException(UpdateTaskException ex, WebRequest request)
    {
        ApiErrorResponse errorDetails = new ApiErrorResponse(Instant.now(), ex.getMessage(), ex.getMessage(),
                HttpStatus.INTERNAL_SERVER_ERROR, request.getDescription(false));
        return new ResponseEntity<>(errorDetails, HttpStatus.INTERNAL_SERVER_ERROR);
    }
}
