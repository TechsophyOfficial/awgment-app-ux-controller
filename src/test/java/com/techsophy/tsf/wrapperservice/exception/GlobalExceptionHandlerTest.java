package com.techsophy.tsf.wrapperservice.exception;

import com.techsophy.tsf.wrapperservice.dto.ApiErrorResponse;
import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.junit.jupiter.MockitoExtension;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.client.RestClientResponseException;
import org.springframework.web.context.request.WebRequest;

import java.time.Instant;

@ExtendWith(MockitoExtension.class)
class GlobalExceptionHandlerTest {
    @Mock
    WebRequest webRequest;
    @InjectMocks
    GlobalExceptionHandler globalExceptionHandler;

    @Test
    void handleGlobalException() {
        Exception exception = new Exception("Exception Message");
        ResponseEntity<ApiErrorResponse> actualResponse = globalExceptionHandler.handleGlobalException(exception, webRequest);
        ApiErrorResponse errorDetails = new ApiErrorResponse(Instant.now(), exception.getMessage(), exception.getMessage(),
                HttpStatus.INTERNAL_SERVER_ERROR, webRequest.getDescription(false));
        ResponseEntity<ApiErrorResponse> expectedResponse =  new ResponseEntity<>(errorDetails, HttpStatus.INTERNAL_SERVER_ERROR);
        Assertions.assertEquals(expectedResponse.getStatusCodeValue(), actualResponse.getStatusCodeValue());
    }

    @Test
    void handleRestClientResponseException() {
        RestClientResponseException restClientResponseException = new RestClientResponseException("msg", 500, "statusText", null, null, null);
        ResponseEntity<ApiErrorResponse> actualResponse = globalExceptionHandler.handleRestClientResponseException(restClientResponseException, webRequest);
        ApiErrorResponse errorDetails = new ApiErrorResponse(Instant.now(), restClientResponseException.getMessage(), restClientResponseException.getMessage(),
                HttpStatus.INTERNAL_SERVER_ERROR, webRequest.getDescription(false));
        ResponseEntity<ApiErrorResponse> expectedResponse =  new ResponseEntity<>(errorDetails, HttpStatus.INTERNAL_SERVER_ERROR);
        Assertions.assertEquals(expectedResponse.getStatusCodeValue(), actualResponse.getStatusCodeValue());
    }

    @Test
    void handleMissingMandatoryDataException() {
        MissingMandatoryDataException missingMandatoryDataException = new MissingMandatoryDataException("code", "msg");
        ResponseEntity<ApiErrorResponse> actualResponse = globalExceptionHandler.handleMissingMandatoryDataException(missingMandatoryDataException, webRequest);
        ApiErrorResponse errorDetails = new ApiErrorResponse(Instant.now(), missingMandatoryDataException.getMessage(), missingMandatoryDataException.getMessage(),
                HttpStatus.INTERNAL_SERVER_ERROR, webRequest.getDescription(false));
        ResponseEntity<ApiErrorResponse> expectedResponse =  new ResponseEntity<>(errorDetails, HttpStatus.INTERNAL_SERVER_ERROR);
        Assertions.assertEquals(expectedResponse.getStatusCodeValue(), actualResponse.getStatusCodeValue());

    }

    @Test
    void handleResumeProcessException() {
        ResumeProcessException resumeProcessException = new ResumeProcessException("code", "msg");
        ResponseEntity<ApiErrorResponse> actualResponse = globalExceptionHandler.handleResumeProcessException(resumeProcessException, webRequest);
        ApiErrorResponse errorDetails = new ApiErrorResponse(Instant.now(), resumeProcessException.getMessage(), resumeProcessException.getMessage(),
                HttpStatus.INTERNAL_SERVER_ERROR, webRequest.getDescription(false));
        ResponseEntity<ApiErrorResponse> expectedResponse =  new ResponseEntity<>(errorDetails, HttpStatus.INTERNAL_SERVER_ERROR);
        Assertions.assertEquals(expectedResponse.getStatusCodeValue(), actualResponse.getStatusCodeValue());
    }

    @Test
    void handleCreateProcessException() {
        ServerErrorException serverErrorException = new ServerErrorException("code", "msg");
        ResponseEntity<ApiErrorResponse> actualResponse = globalExceptionHandler.handleCreateProcessException(serverErrorException, webRequest);
        ApiErrorResponse errorDetails = new ApiErrorResponse(Instant.now(), serverErrorException.getMessage(), serverErrorException.getMessage(),
                HttpStatus.INTERNAL_SERVER_ERROR, webRequest.getDescription(false));
        ResponseEntity<ApiErrorResponse> expectedResponse =  new ResponseEntity<>(errorDetails, HttpStatus.INTERNAL_SERVER_ERROR);
        Assertions.assertEquals(expectedResponse.getStatusCodeValue(), actualResponse.getStatusCodeValue());
    }

    @Test
    void handleUpdateTaskException() {
        UpdateTaskException updateTaskException = new UpdateTaskException("code", "msg");
        ResponseEntity<ApiErrorResponse> actualResponse = globalExceptionHandler.handleUpdateTaskException(updateTaskException, webRequest);
        ApiErrorResponse errorDetails = new ApiErrorResponse(Instant.now(), updateTaskException.getMessage(), updateTaskException.getMessage(),
                HttpStatus.INTERNAL_SERVER_ERROR, webRequest.getDescription(false));
        ResponseEntity<ApiErrorResponse> expectedResponse =  new ResponseEntity<>(errorDetails, HttpStatus.INTERNAL_SERVER_ERROR);
        Assertions.assertEquals(expectedResponse.getStatusCodeValue(), actualResponse.getStatusCodeValue());
    }
}