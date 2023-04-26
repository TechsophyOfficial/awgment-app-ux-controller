package com.techsophy.tsf.wrapperservice.config;

import com.techsophy.tsf.wrapperservice.utils.TokenUtils;
import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.MockitoAnnotations;
import org.springframework.test.util.ReflectionTestUtils;

import static org.mockito.Mockito.*;

class CamundaModifiedPathURLTest {
    @Mock
    TokenUtils tokenUtils;
    @InjectMocks
    CamundaModifiedPathURL camundaModifiedPathURL;

    String gatewayURI;
    String relativeUri;

    @BeforeEach
    void setUp() {
        MockitoAnnotations.openMocks(this);

        when(tokenUtils.getTokenFromContext()).thenReturn("getTokenFromContextResponse");

        ReflectionTestUtils.setField(camundaModifiedPathURL,"defaultRealm", "techsophy-platform");

        ReflectionTestUtils.setField(camundaModifiedPathURL,"gatewayURI","http://localhost:8080");

        ReflectionTestUtils.setField(this,"gatewayURI","http://localhost:8080");

        ReflectionTestUtils.setField(this,"relativeUri","/workflow/v1/process/start");
    }

    @Test
    void testAllServiceMethodsForSharedWorkflowEngine() {

        ReflectionTestUtils.setField(camundaModifiedPathURL,"sharedWorkflowEngine", "true");

        ReflectionTestUtils.setField(camundaModifiedPathURL,"camundaServletContextPath", "/camunda");

        when(tokenUtils.getIssuerFromToken(anyString())).thenReturn("techsophy-platform");

        String expectedOutput = gatewayURI + "/camunda" + relativeUri;
        String actualOutput = camundaModifiedPathURL.getCamundaPathUri(relativeUri);
        Assertions.assertEquals(expectedOutput, actualOutput);
    }

    @Test
    void testAllServiceMethodsForTechsophyPlatformWorkflowEngine() {

        ReflectionTestUtils.setField(camundaModifiedPathURL,"sharedWorkflowEngine", "false");

        ReflectionTestUtils.setField(camundaModifiedPathURL,"camundaServletContextPath", "/camunda");

        when(tokenUtils.getIssuerFromToken(anyString())).thenReturn("techsophy-platform");
        String expectedOutput = gatewayURI + "/camunda" + relativeUri;
        String actualOutput = camundaModifiedPathURL.getCamundaPathUri(relativeUri);
        Assertions.assertEquals(expectedOutput, actualOutput);
    }

    @Test
    void testAllServiceMethodsForMedunitedWorkflowEngine() {

        ReflectionTestUtils.setField(camundaModifiedPathURL,"sharedWorkflowEngine", "false");

        ReflectionTestUtils.setField(camundaModifiedPathURL,"camundaServletContextPath", "/medunited/camunda");

        when(tokenUtils.getIssuerFromToken(anyString())).thenReturn("medunited");
        String expectedOutput = gatewayURI + "/medunited/camunda" + relativeUri;
        String actualOutput = camundaModifiedPathURL.getCamundaPathUri(relativeUri);
        Assertions.assertEquals(expectedOutput, actualOutput);
    }
    @Test
    void testAllServiceMethodsForTrovityWorkflowEngine() {

        ReflectionTestUtils.setField(camundaModifiedPathURL,"sharedWorkflowEngine", "false");

        ReflectionTestUtils.setField(camundaModifiedPathURL,"camundaServletContextPath", "/trovity/camunda");

        when(tokenUtils.getIssuerFromToken(anyString())).thenReturn("trovity");
        String expectedOutput = gatewayURI + "/trovity/camunda" + relativeUri;
        String actualOutput = camundaModifiedPathURL.getCamundaPathUri(relativeUri);
        Assertions.assertEquals(expectedOutput, actualOutput);
    }
}