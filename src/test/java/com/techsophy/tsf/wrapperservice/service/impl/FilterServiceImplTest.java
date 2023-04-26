package com.techsophy.tsf.wrapperservice.service.impl;

import com.fasterxml.jackson.core.JsonProcessingException;
import com.fasterxml.jackson.databind.ObjectMapper;
import com.techsophy.tsf.wrapperservice.config.CamundaModifiedPathURL;
import com.techsophy.tsf.wrapperservice.config.GlobalMessageSource;
import com.techsophy.tsf.wrapperservice.constants.MessageConstants;
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
import java.util.stream.Collectors;

import static com.techsophy.tsf.wrapperservice.constants.ApplicationConstants.CAMUNDA_SERVLET_CONTEXT_PATH_VARIABLE;
import static com.techsophy.tsf.wrapperservice.constants.ApplicationConstants.GATEWAY_URI_VARIABLE;
import static com.techsophy.tsf.wrapperservice.constants.ApplicationEndpointConstants.FILTER;
import static com.techsophy.tsf.wrapperservice.constants.CamundaApiConstants.ENGINE_REST;
import static com.techsophy.tsf.wrapperservice.constants.CamundaApiConstants.POST;
import static com.techsophy.tsf.wrapperservice.constants.MessageConstants.GET;
import static com.techsophy.tsf.wrapperservice.constants.MessageConstants.URL_SEPERATOR;
import static org.mockito.ArgumentMatchers.any;
import static org.mockito.ArgumentMatchers.anyString;
import static org.mockito.Mockito.verify;
import static org.mockito.Mockito.when;

@ExtendWith(MockitoExtension.class)
class FilterServiceImplTest {
    @Mock
    CamundaModifiedPathURL camundaModifiedPathURL;
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
    private String id = "id";
    private String url;

    @BeforeEach
    void setUp() {
        ReflectionTestUtils.setField(filterService, "camundaServletContextPath", "http://apigateway.techsophy.com");
        ReflectionTestUtils.setField(filterService, "gatewayURI", "http://apigateway.techsophy.com");
        filterDTO = new FilterDTO("rType", "name", "owner", Map.of("key", "val"), Map.of("key", "val"));
        id = "id";
        url = gatewayURI + camundaServletContextPath +ENGINE_REST+FILTER+URL_SEPERATOR+id+ MessageConstants.COUNT;
        when(camundaModifiedPathURL.getCamundaPathUri(anyString())).thenReturn("https://localhost:8080");
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

        List<Map<String, Object>> actualOutput = filterService.executeFilter("id", executeFilterDTO, "", "first", "max", true);
        List<Map<String,Object>>  filterLIst = objectMapper.readValue(response,List.class);
        List<Map<String, Object>> expectedOutput = filterLIst.stream().filter(fi-> fi.get("assignee")==null || !(fi.get("assignee").equals("bhavya"))).collect(Collectors.toList());

        Assertions.assertEquals(expectedOutput, actualOutput);
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

        List<Map<String,Object>> actualOutput = filterService.executeFilter("id", executeFilterDTO, "", "first", "max", false);
        List<Map<String,Object>> expectedOutput = objectMapper.readValue(response,List.class);
        Assertions.assertEquals(expectedOutput, actualOutput);
    }

    @Test
    void getFilterByIdTest() throws JsonProcessingException {
        FilterResponseDTO actualOutput = filterService.getFilterById(id);
        String response = webClientWrapper.webclientRequest(webClient,url,GET,null);
        FilterResponseDTO expectedOutput = objectMapper.readValue(response, FilterResponseDTO.class);
        Assertions.assertEquals(expectedOutput, actualOutput);
    }

    @Test
    void filterCountTest() throws JsonProcessingException {
        Map<String, Object> map1 = Map.of("assignee", "val1");
        Map<String, Object> map2 = Map.of("bhavya", "val2");
        List list = List.of(map1, map2);
        FilterCountDTO filterCountDTO = new FilterCountDTO(list);

        FilterCountResponseDTO actualOutput = filterService.filterCount("id", filterCountDTO);
        String response = webClientWrapper.webclientRequest(webClient,url,POST,filterCountDTO);
        FilterCountResponseDTO expectedOutput = objectMapper.readValue(response, FilterCountResponseDTO.class);
        Assertions.assertEquals(expectedOutput, actualOutput);
    }

    @Test
    void getFormVariablesTest() throws JsonProcessingException {
        Map<String, Object> actualOutput = filterService.getFormVariables("id");
        String response = webClientWrapper.webclientRequest(webClient,url,GET,null);
        Map<String, Object> expectedOutput = objectMapper.readValue(response, Map.class);
        Assertions.assertEquals(expectedOutput, actualOutput);
    }
}