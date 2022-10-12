package com.techsophy.tsf.wrapperservice.aspect;

import org.aspectj.lang.JoinPoint;
import org.aspectj.lang.Signature;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.Mockito;
import org.mockito.junit.jupiter.MockitoExtension;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import static org.mockito.ArgumentMatchers.anyString;
import static org.mockito.Mockito.times;
import static org.mockito.Mockito.verify;

@ExtendWith(MockitoExtension.class)
class LoggingTest {

    @Mock
    Logger logger;

    @Mock
    JoinPoint joinPoint;
    @Mock
    Signature signature;

    @InjectMocks
    Logging logging;

    @Test
    void beforeControllerTest() {
        Mockito.when(joinPoint.getSignature()).thenReturn(signature);
        Mockito.when(signature.getName()).thenReturn("Signature_Name");
        logging.beforeController(joinPoint);
        verify(logger, times(1)).info(anyString());
    }

    @Test
    void afterControllerTest() {
        Mockito.when(joinPoint.getSignature()).thenReturn(signature);
        Mockito.when(signature.getName()).thenReturn("Signature_Name");
        logging.afterController(joinPoint);
        verify(joinPoint, times(1)).getSignature();
    }

    @Test
    void beforeServiceTest() {
        Mockito.when(joinPoint.getSignature()).thenReturn(signature);
        Mockito.when(signature.getName()).thenReturn("Signature_Name");
        logging.beforeService(joinPoint);
        verify(joinPoint, times(1)).getSignature();
    }

    @Test
    void afterServiceTest() {
        Mockito.when(joinPoint.getSignature()).thenReturn(signature);
        Mockito.when(signature.getName()).thenReturn("Signature_Name");
        logging.afterService(joinPoint);
        verify(joinPoint, times(1)).getSignature();
    }

    @Test
    void logAfterThrowingControllerTest() {
        Exception exception = Mockito.mock(Exception.class);
        Mockito.when(joinPoint.getSignature()).thenReturn(signature);
        Mockito.when(signature.getName()).thenReturn("Signature_Name");
        logging.logAfterThrowingController(joinPoint, exception);
        verify(joinPoint, times(1)).getSignature();
    }

    @Test
    void logAfterThrowingServiceTest() {
        Exception exception = Mockito.mock(Exception.class);
        Mockito.when(joinPoint.getSignature()).thenReturn(signature);
        Mockito.when(signature.getName()).thenReturn("Signature_Name");
        logging.logAfterThrowingService(joinPoint, exception);
        verify(joinPoint, times(1)).getSignature();
    }
}