package com.techsophy.tsf.wrapperservice.config;

import com.techsophy.tsf.wrapperservice.config.UxControllerCamundaServletContextPath;
import com.techsophy.tsf.wrapperservice.utils.TokenUtils;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.MockitoAnnotations;
import org.springframework.test.util.ReflectionTestUtils;

import static org.mockito.Mockito.*;

class UxControllerCamundaServletContextPathTest {
    @Mock
    TokenUtils tokenUtils;
    @InjectMocks
    UxControllerCamundaServletContextPath uxControllerCamundaServletContextPath;

    @BeforeEach
    void setUp() {
        MockitoAnnotations.openMocks(this);
    }

    @Test
    void testAllServiceMethods() {
        when(tokenUtils.getIssuerFromToken(anyString())).thenReturn("techsophy-platform");
        when(tokenUtils.getTokenFromContext()).thenReturn("getTokenFromContextResponse");
        ReflectionTestUtils.setField(uxControllerCamundaServletContextPath,"defaultRealm", "techsophy-platform");

        uxControllerCamundaServletContextPath.getCamundaPathUri();
    }
}