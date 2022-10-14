package com.techsophy.tsf.wrapperservice.config;

import com.fasterxml.jackson.core.JsonProcessingException;
import com.fasterxml.jackson.databind.ObjectMapper;
import com.techsophy.tsf.wrapperservice.utils.WebClientWrapper;
import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.Mockito;
import org.mockito.junit.jupiter.MockitoExtension;
import org.mockito.junit.jupiter.MockitoSettings;
import org.mockito.quality.Strictness;
import org.springframework.security.core.GrantedAuthority;
import org.springframework.security.core.authority.SimpleGrantedAuthority;
import org.springframework.security.oauth2.jwt.Jwt;
import org.springframework.test.util.ReflectionTestUtils;
import org.springframework.web.reactive.function.client.WebClient;

import java.nio.file.AccessDeniedException;
import java.util.ArrayList;
import java.util.Collection;
import java.util.List;
import java.util.Map;
import java.util.stream.Collectors;

import static com.techsophy.tsf.wrapperservice.constants.ApplicationEndpointConstants.CLIENT_ROLES;
import static org.mockito.ArgumentMatchers.*;

@ExtendWith(MockitoExtension.class)
@MockitoSettings(strictness = Strictness.LENIENT)
class JWTRoleConverterTest {
    @Mock
    WebClientWrapper webClientWrapper;
    @Mock
    ObjectMapper objectMapper;

    @InjectMocks
    JWTRoleConverter jwtRoleConverter;

    @BeforeEach
    void setUp() {
        ReflectionTestUtils.setField(jwtRoleConverter, "keyCloakApi", "http://apigateway.techsophy.com");
    }

    @Test
    void convertTest() throws JsonProcessingException {
        Jwt jwt = Mockito.mock(Jwt.class);
        List<String> awgmentRolesList = new ArrayList<>();
        WebClient client = Mockito.mock(WebClient.class);
        String userResponce = "userResponse";
        Mockito.when(webClientWrapper.webclientRequest(any(), any(), any(), any())).thenReturn(userResponce);
        Mockito.when(objectMapper.readValue(userResponce, Map.class)).thenReturn(Map.of("key", "val"));

        Collection<GrantedAuthority> actualOutput = jwtRoleConverter.convert(jwt);
        Collection<GrantedAuthority> expectedOutput = (awgmentRolesList).stream().map(SimpleGrantedAuthority::new).collect(Collectors.toList());
        Assertions.assertEquals(expectedOutput, actualOutput);
    }

    @Test
    void convertTestWhileThrowingException() throws JsonProcessingException {
        Jwt jwt = Mockito.mock(Jwt.class);
        List<String> awgmentRolesList = new ArrayList<>();
        WebClient client = Mockito.mock(WebClient.class);
        String userResponce = "";
        Mockito.when(webClientWrapper.webclientRequest(any(), any(), any(), any())).thenReturn(userResponce);

        Assertions.assertThrows(AccessDeniedException.class, () -> jwtRoleConverter.convert(jwt));
    }

    @Test
    void convertTestWhileKeyAsClientRoles() throws JsonProcessingException {
        Jwt jwt = Mockito.mock(Jwt.class);
        List<String> awgmentRolesList = new ArrayList<>();
        String userResponce = "userResponse";
        Mockito.when(webClientWrapper.webclientRequest(any(), any(), any(), any())).thenReturn(userResponce);
        Mockito.when(objectMapper.readValue(userResponce, Map.class)).thenReturn(Map.of("key", "val"));
        Mockito.when(objectMapper.readValue(userResponce,Map.class)).thenReturn(Map.of(CLIENT_ROLES, "val"));
        Mockito.when(objectMapper.convertValue(any(), eq(List.class))).thenReturn(List.of());

        Collection<GrantedAuthority> actualOutput = jwtRoleConverter.convert(jwt);
        Collection<GrantedAuthority> expectedOutput = (awgmentRolesList).stream().map(SimpleGrantedAuthority::new).collect(Collectors.toList());
        Assertions.assertEquals(expectedOutput, actualOutput);
    }
}