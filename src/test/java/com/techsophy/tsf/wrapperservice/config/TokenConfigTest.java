package com.techsophy.tsf.wrapperservice.config;

import com.techsophy.tsf.wrapperservice.exception.InvalidInputException;
import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.MethodOrderer;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.TestMethodOrder;
import org.junit.jupiter.api.extension.ExtendWith;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.MockedStatic;
import org.mockito.Mockito;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.test.context.ActiveProfiles;
import org.springframework.test.context.junit.jupiter.SpringExtension;
import org.springframework.web.context.request.RequestAttributes;
import org.springframework.web.context.request.RequestContextHolder;
import org.springframework.web.context.request.ServletRequestAttributes;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import static org.mockito.Mockito.mockStatic;
import static org.mockito.Mockito.when;
import static org.springframework.http.HttpHeaders.AUTHORIZATION;

@SpringBootTest
@ExtendWith({SpringExtension.class})
@TestMethodOrder(MethodOrderer.OrderAnnotation.class)
@ActiveProfiles("test")
class TokenConfigTest {
    @InjectMocks
    TokenConfig tokenConfig;
    @Test
    void getBearerTokenTestForException() throws Exception
    {
        ServletRequestAttributes requestAttributes = Mockito.mock(ServletRequestAttributes.class);
        HttpServletRequest httpServletRequest = Mockito.mock(HttpServletRequest.class);
        try (MockedStatic<RequestContextHolder> requestContextHolderMockedStatic = Mockito.mockStatic(RequestContextHolder.class)) {
            requestContextHolderMockedStatic.when(RequestContextHolder::getRequestAttributes).thenReturn(requestAttributes);
            Mockito.when(requestAttributes.getRequest()).thenReturn(httpServletRequest);
            Mockito.when(httpServletRequest.getHeader(AUTHORIZATION)).thenReturn("token");
            String response = TokenConfig.getBearerToken();
            Assertions.assertNotNull(response);
        }
    }
    @Test
    void getBearerToken() throws Exception
    {
        try(MockedStatic<RequestContextHolder> rc = Mockito.mockStatic(RequestContextHolder.class)) {
           rc.when(()->RequestContextHolder.getRequestAttributes()).thenReturn(null);
           Assertions.assertThrows(NullPointerException.class,()-> TokenConfig.getBearerToken());
        }
    }


}
