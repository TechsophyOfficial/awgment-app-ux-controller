package com.techsophy.tsf.wrapperservice.service.impl;

import com.fasterxml.jackson.core.JsonProcessingException;
import com.fasterxml.jackson.databind.ObjectMapper;
import com.techsophy.tsf.wrapperservice.config.GlobalMessageSource;
import com.techsophy.tsf.wrapperservice.dto.*;
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
import org.springframework.beans.factory.annotation.Value;
import org.springframework.test.util.ReflectionTestUtils;
import org.springframework.web.reactive.function.client.WebClient;

import java.util.List;
import java.util.Map;

import static com.techsophy.tsf.wrapperservice.constants.ApplicationConstants.CAMUNDA_SERVLET_CONTEXT_PATH_VARIABLE;
import static com.techsophy.tsf.wrapperservice.constants.ApplicationConstants.GATEWAY_URI_VARIABLE;
import static com.techsophy.tsf.wrapperservice.constants.CamundaApiConstants.POST;
import static org.junit.jupiter.api.Assertions.*;
import static org.mockito.ArgumentMatchers.any;
import static org.mockito.ArgumentMatchers.anyString;
import static org.mockito.Mockito.times;
import static org.mockito.Mockito.verify;

@ExtendWith(MockitoExtension.class)
class FilterServiceImplTest {
    @Mock
    TokenUtils tokenUtils;
    @Mock
    WebClient webClient;
    @Mock
    WebClientWrapper webClientWrapper;
    @Mock
    TokenUtils utils;
    @Mock
    ObjectMapper objectMapper;
    @Mock
    GlobalMessageSource globalMessageSource;

    @InjectMocks
    FilterServiceImpl filterService;

    @Value(CAMUNDA_SERVLET_CONTEXT_PATH_VARIABLE)
    private String camundaServletContextPath;

    @Value(GATEWAY_URI_VARIABLE)
    private String gatewayURI;
    private FilterDTO filterDTO;

    @BeforeEach
    void setUp() {
        ReflectionTestUtils.setField(filterService, "camundaServletContextPath", "http://apigateway.techsophy.com");
        ReflectionTestUtils.setField(filterService, "gatewayURI", "http://apigateway.techsophy.com");
        filterDTO = new FilterDTO("rType", "name", "owner", Map.of("key", "val"), Map.of("key", "val"));
    }

    @Test
    void createFilterTest() throws JsonProcessingException {
        String response = "response";
        FilterResponseDTO actualOutput = filterService.createFilter(filterDTO);
        FilterResponseDTO expectedOutput = objectMapper.readValue(response, FilterResponseDTO.class);

        Assertions.assertEquals(expectedOutput, actualOutput);
    }

    @Test
    void executeFilterTest() throws JsonProcessingException {
        Map<String, Object> map1 = Map.of("assignee", "val1");
        Map<String, Object> map2 = Map.of("bhavya", "val2");
        List list = List.of(map1, map2);
        ExecuteFilterDTO executeFilterDTO = new ExecuteFilterDTO(list);
        String response = "response";
        Mockito.when(webClientWrapper.webclientRequest(any(),anyString(),anyString(),any(ExecuteFilterDTO.class))).thenReturn(response);
        Mockito.when(objectMapper.readValue(response,List.class)).thenReturn(list);

        filterService.executeFilter("id", executeFilterDTO, "", "first", "max", true);
        verify(webClientWrapper, times(1)).createWebClient(any());
    }

    @Test
    void executeFilterTestWhileInvolvedUserIsFalse() throws JsonProcessingException {
        Map<String, Object> map1 = Map.of("assignee", "val1");
        Map<String, Object> map2 = Map.of("bhavya", "val2");
        List list = List.of(map1, map2);
        ExecuteFilterDTO executeFilterDTO = new ExecuteFilterDTO(list);
        String response = "response";
        Mockito.when(webClientWrapper.webclientRequest(any(),anyString(),anyString(),any(ExecuteFilterDTO.class))).thenReturn(response);
        Mockito.when(objectMapper.readValue(response,List.class)).thenReturn(list);

        filterService.executeFilter("id", executeFilterDTO, "", "first", "max", false);
        verify(webClientWrapper, times(1)).createWebClient(any());
    }

    @Test
    void getFilterByIdTest() throws JsonProcessingException {
        filterService.getFilterById("id");
        verify(webClientWrapper, times(1)).createWebClient(any());
    }

    @Test
    void filterCountTest() throws JsonProcessingException {
        Map<String, Object> map1 = Map.of("assignee", "val1");
        Map<String, Object> map2 = Map.of("bhavya", "val2");
        List list = List.of(map1, map2);
        FilterCountDTO filterCountDTO = new FilterCountDTO(list);

        filterService.filterCount("id", filterCountDTO);
        verify(webClientWrapper, times(1)).createWebClient(any());
    }

    @Test
    void getFormVariablesTest() throws JsonProcessingException {
        filterService.getFormVariables("id");
        verify(webClientWrapper, times(1)).createWebClient(any());
    }
}