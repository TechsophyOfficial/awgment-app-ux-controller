package com.techsophy.tsf.wrapperservice.service.impl;

import com.fasterxml.jackson.core.JsonProcessingException;
import com.fasterxml.jackson.databind.ObjectMapper;
import com.techsophy.tsf.wrapperservice.config.GlobalMessageSource;
import com.techsophy.tsf.wrapperservice.dto.*;
import com.techsophy.tsf.wrapperservice.exception.InvalidInputException;
import com.techsophy.tsf.wrapperservice.utils.TokenUtils;
import com.techsophy.tsf.wrapperservice.utils.WebClientWrapper;
import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.Mockito;
import org.mockito.junit.jupiter.MockitoExtension;
import org.springframework.test.util.ReflectionTestUtils;
import org.springframework.web.reactive.function.client.WebClient;

import java.util.List;
import java.util.Map;

import static org.mockito.ArgumentMatchers.any;
import static org.mockito.ArgumentMatchers.anyString;
import static org.mockito.Mockito.times;
import static org.mockito.Mockito.verify;

@ExtendWith(MockitoExtension.class)
class AllTasksServiceImplTest {
    @Mock
    WebClientWrapper webClientWrapper;
    @Mock
    WebClient webClient;
    @Mock
    TokenUtils tokenUtils;
    @Mock
    ObjectMapper objectMapper;
    @Mock
    GlobalMessageSource globalMessageSource;

    @InjectMocks
    AllTasksServiceImpl allTasksService;

    AllTasksCountDTO allTasksCountDTO = new AllTasksCountDTO();

    @BeforeEach
    void setUp() {
        ReflectionTestUtils.setField(allTasksService, "camundaServletContextPath", "http://apigateway.techsophy.com");
        ReflectionTestUtils.setField(allTasksService, "gatewayURI", "http://apigateway.techsophy.com");
        allTasksCountDTO.setActive(true);
        allTasksCountDTO.setSorting(List.of(Map.of("key", "val")));
    }

    @Test
    void allTasksCountTest() throws JsonProcessingException {
        Mockito.when(webClientWrapper.createWebClient(tokenUtils.getTokenFromContext())).thenReturn(webClient);
        String response = "response";
        Mockito.when(webClientWrapper.webclientRequest(any(WebClient.class), anyString(), anyString(), any(AllTasksCountDTO.class))).thenReturn(response);

        TaskCountDTO actualOutput = allTasksService.allTasksCount(allTasksCountDTO);
        TaskCountDTO expectedOutput = objectMapper.readValue(response,TaskCountDTO.class);
        Assertions.assertEquals(expectedOutput, actualOutput);
    }

    @Test
    void allTasksCountTestWhileThrowingException() throws JsonProcessingException {
        Mockito.when(webClientWrapper.createWebClient(tokenUtils.getTokenFromContext())).thenReturn(webClient);
        String response = "";
        Mockito.when(webClientWrapper.webclientRequest(any(WebClient.class), anyString(), anyString(), any(AllTasksCountDTO.class))).thenReturn(response);

        Assertions.assertThrows(InvalidInputException.class, () -> allTasksService.allTasksCount(allTasksCountDTO));
    }

    @Test
    void allTasksTest() throws JsonProcessingException {
        String response = "response";
        Mockito.when(webClientWrapper.createWebClient(tokenUtils.getTokenFromContext())).thenReturn(webClient);
        Mockito.when(webClientWrapper.webclientRequest(any(WebClient.class), anyString(), anyString(), any(AllTasksCountDTO.class))).thenReturn(response);
        List<AllTasksDTO> actualOutput = allTasksService.allTasks(allTasksCountDTO, 1, 1);
        List<AllTasksDTO> expectedOutput = objectMapper.readValue(response,List.class);
        Assertions.assertEquals(expectedOutput, actualOutput);
    }

    @Test
    void allTasksTestWhileThrowingException() throws JsonProcessingException {
        String response = "";
        Mockito.when(webClientWrapper.createWebClient(tokenUtils.getTokenFromContext())).thenReturn(webClient);
        Mockito.when(webClientWrapper.webclientRequest(any(WebClient.class), anyString(), anyString(), any(AllTasksCountDTO.class))).thenReturn(response);
        Assertions.assertThrows(InvalidInputException.class, () -> allTasksService.allTasks(allTasksCountDTO, 1, 1));
    }

    @Test
    void allTaskCaseInstanceTest() throws JsonProcessingException {
        String response = "response";
        Mockito.when(webClientWrapper.createWebClient(tokenUtils.getTokenFromContext())).thenReturn(webClient);
        Mockito.when(webClientWrapper.webclientRequest(any(WebClient.class), anyString(), anyString(), anyString())).thenReturn(response);
        AllTaskCaseInstanceDTO actualOutput = allTasksService.allTaskCaseInstance("id");
        AllTaskCaseInstanceDTO expectedOutput = objectMapper.readValue(response,AllTaskCaseInstanceDTO.class);
        Assertions.assertEquals(expectedOutput, actualOutput);
    }

    @Test
    void allTaskCaseInstanceTestWhileThrowingException() throws JsonProcessingException {
        String response = "";
        Mockito.when(webClientWrapper.createWebClient(tokenUtils.getTokenFromContext())).thenReturn(webClient);
        Mockito.when(webClientWrapper.webclientRequest(any(WebClient.class), anyString(), anyString(), anyString())).thenReturn(response);
        Assertions.assertThrows(InvalidInputException.class, () -> allTasksService.allTaskCaseInstance("id"));
    }

    @Test
    void allTaskVariablesTest() throws JsonProcessingException {
        String response = "response";
        Mockito.when(webClientWrapper.createWebClient(tokenUtils.getTokenFromContext())).thenReturn(webClient);
        Mockito.when(webClientWrapper.webclientRequest(any(WebClient.class), anyString(), anyString(), anyString())).thenReturn(response);
        AllTaskFormsDTO actualOutput = allTasksService.allTaskVariables("id");
        AllTaskFormsDTO expectedOutput = objectMapper.readValue(response,AllTaskFormsDTO.class);
        Assertions.assertEquals(expectedOutput, actualOutput);
    }

    @Test
    void allTaskVariablesTestWhileThrowingException() throws JsonProcessingException {
        String response = "";
        Mockito.when(webClientWrapper.createWebClient(tokenUtils.getTokenFromContext())).thenReturn(webClient);
        Mockito.when(webClientWrapper.webclientRequest(any(WebClient.class), anyString(), anyString(), anyString())).thenReturn(response);
        Assertions.assertThrows(InvalidInputException.class, () -> allTasksService.allTaskVariables("id"));
    }

    @Test
    void allTaskFormVariablesTest() throws JsonProcessingException {
        String response = "response";
        Mockito.when(webClientWrapper.createWebClient(tokenUtils.getTokenFromContext())).thenReturn(webClient);
        Mockito.when(webClientWrapper.webclientRequest(any(WebClient.class), anyString(), anyString(), anyString())).thenReturn(response);
        AllTaskFormVariablesDTO actualOutput = allTasksService.allTaskFormVariables("id");
        AllTaskFormVariablesDTO expectedOutput = objectMapper.readValue(response,AllTaskFormVariablesDTO.class);
        Assertions.assertEquals(expectedOutput, actualOutput);
    }

    @Test
    void allTaskFormVariablesTestWhileThrowingException() {
        String response = "";
        Mockito.when(webClientWrapper.createWebClient(tokenUtils.getTokenFromContext())).thenReturn(webClient);
        Mockito.when(webClientWrapper.webclientRequest(any(WebClient.class), anyString(), anyString(), anyString())).thenReturn(response);
        Assertions.assertThrows(InvalidInputException.class, () -> allTasksService.allTaskFormVariables("id"));
    }

    @Test
    void allTaskCaseActivityInstanceTest() throws JsonProcessingException {
        String response = "response";
        Mockito.when(webClientWrapper.createWebClient(tokenUtils.getTokenFromContext())).thenReturn(webClient);
        Mockito.when(webClientWrapper.webclientRequest(any(WebClient.class), anyString(), anyString(), anyString())).thenReturn(response);
        List<CaseActivityInstanceDTO> actualOutput = allTasksService.allTaskCaseActivityInstance("id");
        List<CaseActivityInstanceDTO> expectedOutput = objectMapper.readValue(response,List.class);
        Assertions.assertEquals(expectedOutput, actualOutput);
    }

    @Test
    void allTaskCaseActivityInstanceTestWhileThrowingException() throws JsonProcessingException {
        String response = "";
        Mockito.when(webClientWrapper.createWebClient(tokenUtils.getTokenFromContext())).thenReturn(webClient);
        Mockito.when(webClientWrapper.webclientRequest(any(WebClient.class), anyString(), anyString(), anyString())).thenReturn(response);
        Assertions.assertThrows(InvalidInputException.class, () -> allTasksService.allTaskCaseActivityInstance("id"));
    }
}