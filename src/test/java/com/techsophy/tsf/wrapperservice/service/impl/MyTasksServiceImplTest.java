package com.techsophy.tsf.wrapperservice.service.impl;

import com.fasterxml.jackson.core.JsonProcessingException;
import com.fasterxml.jackson.databind.ObjectMapper;
import com.techsophy.tsf.wrapperservice.config.UxControllerCamundaServletContextPath;
import com.techsophy.tsf.wrapperservice.config.GlobalMessageSource;
import com.techsophy.tsf.wrapperservice.dto.MyTasksDTO;
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

import java.util.List;
import java.util.Map;

import static com.techsophy.tsf.wrapperservice.constants.ApplicationConstants.CAMUNDA_SERVLET_CONTEXT_PATH_VARIABLE;
import static com.techsophy.tsf.wrapperservice.constants.ApplicationConstants.GATEWAY_URI_VARIABLE;
import static com.techsophy.tsf.wrapperservice.constants.CamundaApiConstants.CASE_INSTANCE_ID;
import static com.techsophy.tsf.wrapperservice.constants.CamundaApiConstants.ENGINE_REST;
import static com.techsophy.tsf.wrapperservice.constants.MessageConstants.*;
import static org.mockito.ArgumentMatchers.any;
import static org.mockito.ArgumentMatchers.anyString;
import static org.mockito.Mockito.verify;

@ExtendWith(MockitoExtension.class)
class MyTasksServiceImplTest {

    @Mock
    UxControllerCamundaServletContextPath uxControllerCamundaServletContextPath;
    @Mock
    WebClientWrapper webClientWrapper;
    @Mock
    TokenUtils tokenUtils;
    @Mock
    ObjectMapper objectMapper;
    @Mock
    GlobalMessageSource globalMessageSource;

    @InjectMocks
    MyTasksServiceImpl myTasksService;

    @Value(CAMUNDA_SERVLET_CONTEXT_PATH_VARIABLE)
    private String camundaServletContextPath;
    @Value(GATEWAY_URI_VARIABLE)
    private String gatewayURI;

    private MyTasksDTO myTasksDTO;

    @BeforeEach
    void setUp() {
        ReflectionTestUtils.setField(myTasksService, "camundaServletContextPath", "http://apigateway.techsophy.com");
        ReflectionTestUtils.setField(myTasksService, "gatewayURI", "http://apigateway.techsophy.com");
        myTasksDTO = new MyTasksDTO(List.of(Map.of("key", "val")), "assignee");
    }

    @Test
    void myTasksCount() {

        String response = "response";
        Mockito.when(webClientWrapper.webclientRequest(any(), anyString(), anyString(), any(MyTasksDTO.class))).thenReturn(response);
        String actualOutput = myTasksService.myTasksCount(myTasksDTO);
        String expectedOutput = objectMapper.convertValue(response,String.class);

        Assertions.assertEquals(expectedOutput, actualOutput);
    }

    @Test
    void getAllTasks() throws JsonProcessingException {
        String response = "response";
        Mockito.when(webClientWrapper.webclientRequest(any(), anyString(), anyString(), any(MyTasksDTO.class))).thenReturn(response);

        List<Map<String, Object>> actualOutput = myTasksService.getAllTasks(myTasksDTO, "1","1");
        List<Map<String, Object>> expectedOutput = objectMapper.readValue(response,List.class);

        Assertions.assertEquals(expectedOutput, actualOutput);
    }

    @Test
    void getMyTasksHistory() throws JsonProcessingException {
        String response = "response";
        String id = "id";
        Mockito.when(webClientWrapper.webclientRequest(any(),anyString(),anyString(),any())).thenReturn(response);
        List<Map<String, Object>> actualOutput = myTasksService.getMyTasksHistory(id);
        String requestURL = gatewayURI + camundaServletContextPath+ENGINE_REST+HISTORY+CASE_ACTIVITY_INSTANCE+CASE_INSTANCE_ID+id;
        List<Map<String, Object>> expectedOutput = objectMapper.readValue(response,List.class);
        Assertions.assertEquals(expectedOutput, actualOutput);
    }

    @Test
    void getMyTasksById() throws JsonProcessingException {
        String response = "response";
        String id = "id";
        Mockito.when(webClientWrapper.webclientRequest(any(),anyString(),anyString(),any())).thenReturn(response);
        Map<String, Object> actualOutput = myTasksService.getMyTasksById(id);
        Map<String, Object> expectedOutput = objectMapper.readValue(response,Map.class);
        Assertions.assertEquals(expectedOutput, actualOutput);
    }
}