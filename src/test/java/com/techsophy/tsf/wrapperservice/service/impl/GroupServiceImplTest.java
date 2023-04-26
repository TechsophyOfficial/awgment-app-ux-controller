package com.techsophy.tsf.wrapperservice.service.impl;

import com.fasterxml.jackson.core.JsonProcessingException;
import com.fasterxml.jackson.databind.ObjectMapper;
import com.techsophy.tsf.wrapperservice.config.CamundaModifiedPathURL;
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
import org.springframework.beans.factory.annotation.Value;
import org.springframework.test.util.ReflectionTestUtils;

import java.util.List;
import java.util.Map;

import static com.techsophy.tsf.wrapperservice.constants.ApplicationConstants.CAMUNDA_SERVLET_CONTEXT_PATH_VARIABLE;
import static com.techsophy.tsf.wrapperservice.constants.ApplicationConstants.GATEWAY_URI_VARIABLE;
import static org.mockito.ArgumentMatchers.any;
import static org.mockito.ArgumentMatchers.anyString;
import static org.mockito.Mockito.when;

@ExtendWith(MockitoExtension.class)
class GroupServiceImplTest {

    @Mock
    CamundaModifiedPathURL camundaModifiedPathURL;
    @Mock
    WebClientWrapper webClientWrapper;
    @Mock
    TokenUtils tokenUtils;
    @Mock
    ObjectMapper objectMapper;
    @Mock
    GlobalMessageSource globalMessageSource;

    @InjectMocks
    GroupServiceImpl groupService;

    @Value(CAMUNDA_SERVLET_CONTEXT_PATH_VARIABLE)
    private String camundaServletContextPath;

    @Value(GATEWAY_URI_VARIABLE)
    private String gatewayURI;

    private  String id = "id";
    String response = "response";
    String emptyResponse = "";

    @BeforeEach
    void setUp() {
        ReflectionTestUtils.setField(groupService, "camundaServletContextPath", "http://apigateway.techsophy.com");
        ReflectionTestUtils.setField(groupService, "gatewayURI", "http://apigateway.techsophy.com");
        when(camundaModifiedPathURL.getCamundaPathUri(anyString())).thenReturn("https://localhost:8080");
    }

    @Test
    void groupCountTest() throws JsonProcessingException {
        List list = List.of(Map.of("key", "val"));
        GroupTaskCountReqDTO dto = new GroupTaskCountReqDTO();
        dto.setSorting(list);
        dto.setCandidateUser("user");

        Mockito.when(webClientWrapper.webclientRequest(any(), anyString(), anyString(), any(GroupTaskCountReqDTO.class))).thenReturn(response);

        TaskCountDTO actualOutput = groupService.groupCount(dto);
        TaskCountDTO expectedOutput = objectMapper.readValue(response, TaskCountDTO.class);
        Assertions.assertEquals(expectedOutput, actualOutput);
    }

    @Test
    void groupCountTestWhileThrowingException() throws JsonProcessingException {
        List list = List.of(Map.of("key", "val"));
        GroupTaskCountReqDTO dto = new GroupTaskCountReqDTO();
        dto.setSorting(list);
        dto.setCandidateUser("user");

        Mockito.when(webClientWrapper.webclientRequest(any(), anyString(), anyString(), any(GroupTaskCountReqDTO.class))).thenReturn(emptyResponse);

        Assertions.assertThrows(InvalidInputException.class, () -> groupService.groupCount(dto));
    }

    @Test
    void groupTaskTest() throws JsonProcessingException {
        List list = List.of(Map.of("key", "val"));
        GroupTaskCountReqDTO dto = new GroupTaskCountReqDTO();
        dto.setSorting(list);
        dto.setCandidateUser("user");

        Mockito.when(webClientWrapper.webclientRequest(any(), anyString(), anyString(), any(GroupTaskCountReqDTO.class))).thenReturn(response);

        List<GroupTaskDTO> actualOutput = groupService.groupTask(dto, 1, 1);
        List<GroupTaskDTO> expectedlOutput = objectMapper.readValue(response,List.class);
        Assertions.assertEquals(expectedlOutput, actualOutput);
    }

    @Test
    void groupTaskTestWhileThrowingException() throws JsonProcessingException {
        List list = List.of(Map.of("key", "val"));
        GroupTaskCountReqDTO dto = new GroupTaskCountReqDTO();
        dto.setSorting(list);
        dto.setCandidateUser("user");

        Mockito.when(webClientWrapper.webclientRequest(any(), anyString(), anyString(), any(GroupTaskCountReqDTO.class))).thenReturn(emptyResponse);

        Assertions.assertThrows(InvalidInputException.class, () -> groupService.groupTask(dto, 1, 1));
    }

    @Test
    void groupTaskHistoryTest() throws JsonProcessingException {
        Mockito.when(webClientWrapper.webclientRequest(any(), anyString(), anyString(), anyString())).thenReturn(response);
        List<GroupTaskHistoryDTO> actualOutput = groupService.groupTaskHistory(id);
        List<GroupTaskHistoryDTO> expectedOutput = objectMapper.readValue(response,List.class);
        Assertions.assertEquals(expectedOutput, actualOutput);
    }

    @Test
    void groupTaskHistoryTestWhileThrowingException() throws JsonProcessingException {
        Mockito.when(webClientWrapper.webclientRequest(any(), anyString(), anyString(), anyString())).thenReturn(emptyResponse);

        Assertions.assertThrows(InvalidInputException.class, () -> groupService.groupTaskHistory(id));
    }

    @Test
    void groupCaseInstanceTest() throws JsonProcessingException {
        Mockito.when(webClientWrapper.webclientRequest(any(), anyString(), anyString(), anyString())).thenReturn(response);

        GroupCaseInstanceDTO actualOutput = groupService.groupCaseInstance(id);
        GroupCaseInstanceDTO expectedOutput = objectMapper.readValue(response, GroupCaseInstanceDTO.class);

        Assertions.assertEquals(expectedOutput, actualOutput);
    }

    @Test
    void groupCaseInstanceTestWhileThrowingException() throws JsonProcessingException {
        Mockito.when(webClientWrapper.webclientRequest(any(), anyString(), anyString(), anyString())).thenReturn(emptyResponse);

        Assertions.assertThrows(InvalidInputException.class, () -> groupService.groupCaseInstance(id));
    }

    @Test
    void groupFormVariablesTest() throws JsonProcessingException {
        Mockito.when(webClientWrapper.webclientRequest(any(), anyString(), anyString(), anyString())).thenReturn(response);

        GroupFormDTo actualOutput = groupService.groupFormVariables(id);
        GroupFormDTo expectedOutput = objectMapper.readValue(response, GroupFormDTo.class);

        Assertions.assertEquals(expectedOutput, actualOutput);
    }

    @Test
    void groupFormVariablesTestWhileThrowingException() throws JsonProcessingException {
        Mockito.when(webClientWrapper.webclientRequest(any(), anyString(), anyString(), anyString())).thenReturn(emptyResponse);

        Assertions.assertThrows(InvalidInputException.class, () -> groupService.groupFormVariables(id));
    }

    @Test
    void groupVariablesTest() throws JsonProcessingException {
        Mockito.when(webClientWrapper.webclientRequest(any(), anyString(), anyString(), anyString())).thenReturn(response);

        AllTaskCaseInstanceDTO actualOutput = groupService.groupVariables(id);
        AllTaskCaseInstanceDTO expectedOutput = objectMapper.readValue(response, AllTaskCaseInstanceDTO.class);

        Assertions.assertEquals(expectedOutput, actualOutput);
    }

    @Test
    void groupVariablesTestWhileThrowingException() throws JsonProcessingException {
        Mockito.when(webClientWrapper.webclientRequest(any(), anyString(), anyString(), anyString())).thenReturn(emptyResponse);

        Assertions.assertThrows(InvalidInputException.class, () -> groupService.groupVariables(id));
    }

    @Test
    void groupCaseDefinitionTest() throws JsonProcessingException {
        Mockito.when(webClientWrapper.webclientRequest(any(), anyString(), anyString(), anyString())).thenReturn(response);

        GroupTaskCaseDefinition actualOutput = groupService.groupCaseDefinition(id);
        GroupTaskCaseDefinition expectedOutput = objectMapper.readValue(response, GroupTaskCaseDefinition.class);

        Assertions.assertEquals(expectedOutput, actualOutput);
    }

    @Test
    void groupCaseDefinitionTestWhileThrowingException() throws JsonProcessingException {
        Mockito.when(webClientWrapper.webclientRequest(any(), anyString(), anyString(), anyString())).thenReturn(emptyResponse);

        Assertions.assertThrows(InvalidInputException.class, () -> groupService.groupCaseDefinition(id));
    }
}