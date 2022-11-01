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
import org.springframework.web.context.request.RequestContextHolder;

import static org.mockito.Mockito.mockStatic;
import static org.mockito.Mockito.when;

@SpringBootTest
@ExtendWith({SpringExtension.class})
@TestMethodOrder(MethodOrderer.OrderAnnotation.class)
@ActiveProfiles("test")
class TokenConfigTest {
    @InjectMocks
    TokenConfig tokenConfig;
    @Test
    void getBearerTokenTest() throws Exception
    {
        TokenConfig.getBearerToken();
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
