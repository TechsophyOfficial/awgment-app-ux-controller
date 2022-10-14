package com.techsophy.tsf.wrapperservice.config;

import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.Mockito;
import org.mockito.junit.jupiter.MockitoExtension;
import org.springframework.security.authentication.AuthenticationManagerResolver;
import org.springframework.security.config.annotation.web.builders.HttpSecurity;

import javax.servlet.http.HttpServletRequest;

import static org.mockito.ArgumentMatchers.any;
import static org.mockito.Mockito.times;
import static org.mockito.Mockito.verify;

@ExtendWith(MockitoExtension.class)
class SecurityConfigTest {

    @Mock
    AuthenticationManagerResolver<HttpServletRequest> authenticationManagerResolver;
    @InjectMocks
    SecurityConfig securityConfig;

    @Test
    void configureTest() throws Exception {
        HttpSecurity httpSecurity = Mockito.mock(HttpSecurity.class);
        Mockito.when(httpSecurity.authorizeRequests(any())).thenReturn(httpSecurity);

        securityConfig.configure(httpSecurity);
        verify(httpSecurity, times(1)).oauth2ResourceServer(any());
    }
}