package com.techsophy.tsf.wrapperservice.utils;

import com.fasterxml.jackson.core.JsonProcessingException;
import com.fasterxml.jackson.core.type.TypeReference;
import com.fasterxml.jackson.databind.ObjectMapper;
import com.fasterxml.jackson.databind.ser.Serializers;
import com.techsophy.tsf.wrapperservice.config.GlobalMessageSource;
import org.apache.commons.lang3.StringUtils;
import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.MockedStatic;
import org.mockito.Mockito;
import org.mockito.junit.jupiter.MockitoExtension;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.core.Authentication;
import org.springframework.security.core.context.SecurityContext;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.security.oauth2.core.user.OAuth2User;
import org.springframework.security.oauth2.jwt.Jwt;
import org.springframework.test.util.ReflectionTestUtils;

import java.util.*;

import static com.techsophy.tsf.wrapperservice.constants.MessageConstants.*;
import static org.mockito.ArgumentMatchers.*;
import static org.mockito.Mockito.times;
import static org.mockito.Mockito.verify;

@ExtendWith(MockitoExtension.class)
class TokenUtilsTest {
    @Mock
    GlobalMessageSource globalMessageSource;
    @Mock
    ObjectMapper objectMapper;
    @Mock
    WebClientWrapper webClientWrapper;
    @Mock
    SecurityContext securityContext;
    @Autowired
    SecurityContextHolder securityContextHolder;

    @InjectMocks
    TokenUtils tokenUtils;

    Logger logger = LoggerFactory.getLogger(TokenUtils.class);

    @BeforeEach
    void setUp() {
        ReflectionTestUtils.setField(tokenUtils, "keyCloakApi", "http://apigateway.techsophy.com");
    }

    @Test
    void getLoggedInUserIdTest() {
        SecurityContext securityContext = Mockito.mock(SecurityContext.class);
        Authentication authentication = Mockito.mock(Authentication.class);
        OAuth2User oAuth2User = Mockito.mock(OAuth2User.class);
        try (MockedStatic<SecurityContextHolder> mockedStatic = Mockito.mockStatic(SecurityContextHolder.class)) {
            mockedStatic.when(SecurityContextHolder::getContext).thenReturn(securityContext);
            Mockito.when(securityContext.getAuthentication()).thenReturn(authentication);
            Mockito.when(authentication.getPrincipal()).thenReturn(oAuth2User);
            Mockito.when(oAuth2User.getName()).thenReturn("name");
            String actualOutput = tokenUtils.getLoggedInUserId();
            String expectedOutput = Optional.of(oAuth2User.getName()).filter(StringUtils::isNotEmpty).orElseThrow(RuntimeException::new);

            Assertions.assertEquals(expectedOutput, actualOutput);
        }
    }

    @Test
    void getLoggedInUserIdTestWhilePrincipalIsInstanceOfJwt() {
        SecurityContext securityContext = Mockito.mock(SecurityContext.class);
        Authentication authentication = Mockito.mock(Authentication.class);
        Jwt jwt = Mockito.mock(Jwt.class);
        try (MockedStatic<SecurityContextHolder> mockedStatic = Mockito.mockStatic(SecurityContextHolder.class)) {
            mockedStatic.when(SecurityContextHolder::getContext).thenReturn(securityContext);
            Mockito.when(securityContext.getAuthentication()).thenReturn(authentication);
            Mockito.when(authentication.getPrincipal()).thenReturn(jwt);

            String actualOutput = tokenUtils.getLoggedInUserId();
            String expectedOutput = jwt.getClaim(PREFERED_USERNAME);

            Assertions.assertEquals(expectedOutput, actualOutput);
        }
    }

    @Test
    void getLoggedInUserIdTestWhileThrowingException() {
        SecurityContext securityContext = Mockito.mock(SecurityContext.class);
        Authentication authentication = Mockito.mock(Authentication.class);
        try (MockedStatic<SecurityContextHolder> mockedStatic = Mockito.mockStatic(SecurityContextHolder.class)) {
            mockedStatic.when(SecurityContextHolder::getContext).thenReturn(securityContext);
            Mockito.when(securityContext.getAuthentication()).thenReturn(authentication);
            Mockito.when(authentication.getPrincipal()).thenReturn("principal");
            Assertions.assertThrows(SecurityException.class, () -> tokenUtils.getLoggedInUserId());
        }
    }

    @Test
    void getLoggedInUserIdTestWhileAuthenticationIsNull() {
        SecurityContext securityContext = Mockito.mock(SecurityContext.class);
        try (MockedStatic<SecurityContextHolder> mockedStatic = Mockito.mockStatic(SecurityContextHolder.class)) {
            mockedStatic.when(SecurityContextHolder::getContext).thenReturn(securityContext);
            Mockito.when(securityContext.getAuthentication()).thenReturn(null);
            Assertions.assertThrows(SecurityException.class, () -> tokenUtils.getLoggedInUserId());
        }
    }

    @Test
    void getLoggedInUserIdTestWhileContextIsNull() {
        try (MockedStatic<SecurityContextHolder> mockedStatic = Mockito.mockStatic(SecurityContextHolder.class)) {
            mockedStatic.when(SecurityContextHolder::getContext).thenReturn(null);
            Assertions.assertThrows(SecurityException.class, () -> tokenUtils.getLoggedInUserId());
        }
    }


    @Test
    void getIssuerFromContextTest() {
        SecurityContext securityContext = Mockito.mock(SecurityContext.class);
        Authentication authentication = Mockito.mock(Authentication.class);
        OAuth2User oAuth2User = Mockito.mock(OAuth2User.class);
        try (MockedStatic<SecurityContextHolder> mockedStatic = Mockito.mockStatic(SecurityContextHolder.class)) {
            mockedStatic.when(SecurityContextHolder::getContext).thenReturn(securityContext);
            Mockito.when(securityContext.getAuthentication()).thenReturn(authentication);
            Mockito.when(authentication.getPrincipal()).thenReturn(oAuth2User);
            Mockito.when(oAuth2User.getName()).thenReturn("name");
            String actualOutput = tokenUtils.getIssuerFromContext();
            String expectedOutput = Optional.of(oAuth2User.getName()).filter(StringUtils::isNotEmpty).orElseThrow(RuntimeException::new);

            Assertions.assertEquals(expectedOutput, actualOutput);
        }
    }

    @Test
    void getIssuerFromContextTestWhilePrincipalIsInstanceOfJwt() {
        SecurityContext securityContext = Mockito.mock(SecurityContext.class);
        Authentication authentication = Mockito.mock(Authentication.class);
        Jwt jwt = Mockito.mock(Jwt.class);
        try (MockedStatic<SecurityContextHolder> mockedStatic = Mockito.mockStatic(SecurityContextHolder.class)) {
            mockedStatic.when(SecurityContextHolder::getContext).thenReturn(securityContext);
            Mockito.when(securityContext.getAuthentication()).thenReturn(authentication);
            Mockito.when(authentication.getPrincipal()).thenReturn(jwt);
            Mockito.when(jwt.getClaim(ISS)).thenReturn("url1/url2/url3");

            String actualOutput = tokenUtils.getIssuerFromContext();
            List<String> issuerUrl= Arrays.asList(jwt.getClaim(ISS).toString().split(URL_SEPERATOR));
            String expectedOutput = issuerUrl.get(issuerUrl.size()-1);

            Assertions.assertEquals(expectedOutput, actualOutput);
        }
    }

    @Test
    void getIssuerFromContextTestWhileThrowingException() {
        SecurityContext securityContext = Mockito.mock(SecurityContext.class);
        Authentication authentication = Mockito.mock(Authentication.class);
        try (MockedStatic<SecurityContextHolder> mockedStatic = Mockito.mockStatic(SecurityContextHolder.class)) {
            mockedStatic.when(SecurityContextHolder::getContext).thenReturn(securityContext);
            Mockito.when(securityContext.getAuthentication()).thenReturn(authentication);
            Mockito.when(authentication.getPrincipal()).thenReturn("principal");
            Assertions.assertThrows(SecurityException.class, () -> tokenUtils.getIssuerFromContext());
        }
    }

    @Test
    void getIssuerFromContextTestWhileAuthenticationIsNull() {
        SecurityContext securityContext = Mockito.mock(SecurityContext.class);
        try (MockedStatic<SecurityContextHolder> mockedStatic = Mockito.mockStatic(SecurityContextHolder.class)) {
            mockedStatic.when(SecurityContextHolder::getContext).thenReturn(securityContext);
            Mockito.when(securityContext.getAuthentication()).thenReturn(null);
            Assertions.assertThrows(SecurityException.class, () -> tokenUtils.getIssuerFromContext());
        }
    }

    @Test
    void getIssuerFromContextTestWhileContextIsNull() {
        try (MockedStatic<SecurityContextHolder> mockedStatic = Mockito.mockStatic(SecurityContextHolder.class)) {
            mockedStatic.when(SecurityContextHolder::getContext).thenReturn(null);
            Assertions.assertThrows(SecurityException.class, () -> tokenUtils.getIssuerFromContext());
        }
    }

    @Test
    void getIssuerFromTokenTest() throws JsonProcessingException {
        String base64 = "eyJpc3MiOiJtZXNzYWdlIn0=";
        String idToken = "Bearer \\."+base64;
        ObjectMapper mapper = Mockito.mock(ObjectMapper.class);
        Base64.Decoder decoder = Mockito.mock(Base64.Decoder.class);
        TypeReference typeReference = Mockito.mock(TypeReference.class);

        try (MockedStatic<Base64> mockedStatic = Mockito.mockStatic(Base64.class)) {
            mockedStatic.when(Base64::getDecoder).thenReturn(decoder);
        }

        String actualOutput = tokenUtils.getIssuerFromToken(idToken);
        Map<String, Object> tokenBody = new HashMap<>();
        tokenBody = Map.of("iss", "message");
        List<String> elements= Arrays.asList(tokenBody.get(ISS).toString().split(URL_SEPERATOR));
        String expectedOutput = elements.get(elements.size()-1);
        Assertions.assertEquals(expectedOutput, actualOutput);
    }

    @Test
    void getTokenFromContextTest() {
        SecurityContext securityContext = Mockito.mock(SecurityContext.class);
        Authentication authentication = Mockito.mock(Authentication.class);
        OAuth2User oAuth2User = Mockito.mock(OAuth2User.class);
        try (MockedStatic<SecurityContextHolder> mockedStatic = Mockito.mockStatic(SecurityContextHolder.class)) {
            mockedStatic.when(SecurityContextHolder::getContext).thenReturn(securityContext);
            Mockito.when(securityContext.getAuthentication()).thenReturn(authentication);
            Mockito.when(authentication.getPrincipal()).thenReturn(oAuth2User);
            Mockito.when(oAuth2User.getName()).thenReturn("name");
            String actualOutput = tokenUtils.getTokenFromContext();
            String expectedOutput = Optional.of(oAuth2User.getName()).filter(StringUtils::isNotEmpty).orElseThrow(RuntimeException::new);

            Assertions.assertEquals(expectedOutput, actualOutput);
        }
    }

    @Test
    void getTokenFromContextTestWhilePrincipalIsInstanceOfJwt() {
        SecurityContext securityContext = Mockito.mock(SecurityContext.class);
        Authentication authentication = Mockito.mock(Authentication.class);
        Jwt jwt = Mockito.mock(Jwt.class);
        try (MockedStatic<SecurityContextHolder> mockedStatic = Mockito.mockStatic(SecurityContextHolder.class)) {
            mockedStatic.when(SecurityContextHolder::getContext).thenReturn(securityContext);
            Mockito.when(securityContext.getAuthentication()).thenReturn(authentication);
            Mockito.when(authentication.getPrincipal()).thenReturn(jwt);

            String actualOutput = tokenUtils.getTokenFromContext();
            String expectedOutput = jwt.getTokenValue();

            Assertions.assertEquals(expectedOutput, actualOutput);
        }
    }

    @Test
    void getTokenFromContextTestWhileThrowingException() {
        SecurityContext securityContext = Mockito.mock(SecurityContext.class);
        Authentication authentication = Mockito.mock(Authentication.class);
        try (MockedStatic<SecurityContextHolder> mockedStatic = Mockito.mockStatic(SecurityContextHolder.class)) {
            mockedStatic.when(SecurityContextHolder::getContext).thenReturn(securityContext);
            Mockito.when(securityContext.getAuthentication()).thenReturn(authentication);
            Mockito.when(authentication.getPrincipal()).thenReturn("principal");
            Assertions.assertThrows(SecurityException.class, () -> tokenUtils.getTokenFromContext());
        }
    }

    @Test
    void getTokenFromContextTestWhileAuthenticationIsNull() {
        SecurityContext securityContext = Mockito.mock(SecurityContext.class);
        try (MockedStatic<SecurityContextHolder> mockedStatic = Mockito.mockStatic(SecurityContextHolder.class)) {
            mockedStatic.when(SecurityContextHolder::getContext).thenReturn(securityContext);
            Mockito.when(securityContext.getAuthentication()).thenReturn(null);
            Assertions.assertThrows(SecurityException.class, () -> tokenUtils.getTokenFromContext());
        }
    }

    @Test
    void getTokenFromContextTestWhileContextIsNull() {
        try (MockedStatic<SecurityContextHolder> mockedStatic = Mockito.mockStatic(SecurityContextHolder.class)) {
            mockedStatic.when(SecurityContextHolder::getContext).thenReturn(null);
            Assertions.assertThrows(SecurityException.class, () -> tokenUtils.getTokenFromContext());
        }
    }

}