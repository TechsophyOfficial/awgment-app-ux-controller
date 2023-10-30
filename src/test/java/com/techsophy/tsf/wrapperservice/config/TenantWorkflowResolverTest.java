package com.techsophy.tsf.wrapperservice.config;

import com.techsophy.tsf.wrapperservice.utils.TokenUtils;
import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.params.ParameterizedTest;
import org.junit.jupiter.params.provider.CsvSource;
import org.junit.jupiter.params.provider.ValueSource;
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

    @ParameterizedTest
    @CsvSource({
            "false,techsophy-platform,/camunda",
            "true,techsophy-platform,/camunda",
            "false,medunited,/medunited/camunda",
            "true,medunited,/camunda"
    })

    void testAllServiceMethodsForSharedWorkflowEngine(String shared, String realm,String expectedFragment) {

        ReflectionTestUtils.setField(tenantWorkflowResolver,"sharedWorkflowEngine", shared);

        ReflectionTestUtils.setField(tenantWorkflowResolver,"camundaServletContextPath", "/camunda");

        when(tokenUtils.getIssuerFromToken(anyString())).thenReturn(realm);

        String expectedOutput = gatewayURI + realm + expectedFragment + relativeUri;
        String actualOutput = tenantWorkflowResolver.getCamundaPathUri(relativeUri);
        Assertions.assertNotEquals(expectedOutput, actualOutput);
    }
}