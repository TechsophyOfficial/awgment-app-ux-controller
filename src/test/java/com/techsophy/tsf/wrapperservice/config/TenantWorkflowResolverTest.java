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

class TenantWorkflowResolverTest {
    @Mock
    TokenUtils tokenUtils;
    @InjectMocks
    TenantWorkflowResolver tenantWorkflowResolver;

    String gatewayURI;
    String relativeUri;

    @BeforeEach
    void setUp() {
        MockitoAnnotations.openMocks(this);

        when(tokenUtils.getTokenFromContext()).thenReturn("getTokenFromContextResponse");

        ReflectionTestUtils.setField(tenantWorkflowResolver,"defaultRealm", "techsophy-platform");

        ReflectionTestUtils.setField(tenantWorkflowResolver,"gatewayURI","http://localhost:8080");

        ReflectionTestUtils.setField(this,"gatewayURI","http://localhost:8080");

        ReflectionTestUtils.setField(this,"relativeUri","/workflow/v1/process/start");
    }

    @Test
    void testAllServiceMethodsForSharedWorkflowEngine() {

        ReflectionTestUtils.setField(tenantWorkflowResolver,"sharedWorkflowEngine", "true");

        ReflectionTestUtils.setField(tenantWorkflowResolver,"camundaServletContextPath", "/camunda");

        when(tokenUtils.getIssuerFromToken(anyString())).thenReturn("techsophy-platform");

        String expectedOutput = gatewayURI + "/camunda" + relativeUri;
        String actualOutput = tenantWorkflowResolver.getCamundaPathUri(relativeUri);
        Assertions.assertEquals(expectedOutput, actualOutput);
    }

    @Test
    void testAllServiceMethodsForTechsophyPlatformWorkflowEngine() {

        ReflectionTestUtils.setField(tenantWorkflowResolver,"sharedWorkflowEngine", "false");

        ReflectionTestUtils.setField(tenantWorkflowResolver,"camundaServletContextPath", "/camunda");

        when(tokenUtils.getIssuerFromToken(anyString())).thenReturn("techsophy-platform");
        String expectedOutput = gatewayURI + "/camunda" + relativeUri;
        String actualOutput = tenantWorkflowResolver.getCamundaPathUri(relativeUri);
        Assertions.assertEquals(expectedOutput, actualOutput);
    }

    @Test
    void testAllServiceMethodsForMedunitedWorkflowEngine() {

        ReflectionTestUtils.setField(tenantWorkflowResolver,"sharedWorkflowEngine", "false");

        ReflectionTestUtils.setField(tenantWorkflowResolver,"camundaServletContextPath", "/medunited/camunda");

        when(tokenUtils.getIssuerFromToken(anyString())).thenReturn("medunited");
        String expectedOutput = gatewayURI + "/medunited/camunda" + relativeUri;
        String actualOutput = tenantWorkflowResolver.getCamundaPathUri(relativeUri);
        Assertions.assertEquals(expectedOutput, actualOutput);
    }
    @Test
    void testAllServiceMethodsForTrovityWorkflowEngine() {

        ReflectionTestUtils.setField(tenantWorkflowResolver,"sharedWorkflowEngine", "false");

        ReflectionTestUtils.setField(tenantWorkflowResolver,"camundaServletContextPath", "/trovity/camunda");

        when(tokenUtils.getIssuerFromToken(anyString())).thenReturn("trovity");
        String expectedOutput = gatewayURI + "/trovity/camunda" + relativeUri;
        String actualOutput = tenantWorkflowResolver.getCamundaPathUri(relativeUri);
        Assertions.assertEquals(expectedOutput, actualOutput);
    }
}