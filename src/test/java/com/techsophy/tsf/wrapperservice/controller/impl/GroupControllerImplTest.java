package com.techsophy.tsf.wrapperservice.controller.impl;

import com.fasterxml.jackson.core.JsonProcessingException;
import com.techsophy.tsf.wrapperservice.constants.MessageConstants;
import com.techsophy.tsf.wrapperservice.dto.*;
import com.techsophy.tsf.wrapperservice.service.GroupService;
import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.Mockito;
import org.mockito.junit.jupiter.MockitoExtension;

import java.util.List;
import java.util.Map;

import static org.junit.jupiter.api.Assertions.*;

@ExtendWith(MockitoExtension.class)
class GroupControllerImplTest {
    @Mock
    GroupService groupService;
    @InjectMocks
    GroupControllerImpl groupController;

    String id;

    @BeforeEach
    void setUp() {
        id = "id";
    }

    @Test
    void groupTaskCountTest() throws JsonProcessingException {
        GroupTaskCountReqDTO groupTaskCountReqDTO = Mockito.mock(GroupTaskCountReqDTO.class);
        ApiResponse<TaskCountDTO> actualOutput = groupController.groupTaskCount(groupTaskCountReqDTO);
        ApiResponse<TaskCountDTO> expectedOutput = new ApiResponse<>(groupService.groupCount(groupTaskCountReqDTO),true, MessageConstants.GET_TASK_COUNT_SUCCESS);
        Assertions.assertEquals(expectedOutput, actualOutput);
    }

    @Test
    void groupTaskTest() throws JsonProcessingException {
        GroupTaskCountReqDTO groupTaskCountReqDTO = Mockito.mock(GroupTaskCountReqDTO.class);
        ApiResponse<List<GroupTaskDTO>> actualOutput = groupController.groupTask(groupTaskCountReqDTO, 1, 1);
        ApiResponse<List<GroupTaskDTO>> expectedOutput = new ApiResponse<>(groupService.groupTask(groupTaskCountReqDTO, 1, 1),true,MessageConstants.GET_GROUP_TASKS);
        Assertions.assertEquals(expectedOutput, actualOutput);
    }

    @Test
    void groupTaskHistoryTest() throws JsonProcessingException {
        ApiResponse<List<GroupTaskHistoryDTO>> actualOutput = groupController.groupTaskHistory(id);
        ApiResponse<List<GroupTaskHistoryDTO>> expectedOutput = new ApiResponse<>(groupService.groupTaskHistory(id),true,MessageConstants. GET_CASE_INSTANCES);
        Assertions.assertEquals(expectedOutput, actualOutput);
    }

    @Test
    void groupCaseInstanceTest() throws JsonProcessingException {
        ApiResponse<GroupCaseInstanceDTO> actualOutput = groupController.groupCaseInstance(id);
        ApiResponse<GroupCaseInstanceDTO> expectedOutput = new ApiResponse<>(groupService.groupCaseInstance(id),true,MessageConstants. GET_CASE_INSTANCES);
        Assertions.assertEquals(expectedOutput, actualOutput);
    }

    @Test
    void groupFormVariablesTest() throws JsonProcessingException {
        ApiResponse<GroupFormDTo> actualOutput = groupController.groupFormVariables(id);
        ApiResponse<GroupFormDTo> expectedOutput = new ApiResponse<>(groupService.groupFormVariables(id),true,MessageConstants. GET_CASE_INSTANCES);
        Assertions.assertEquals(expectedOutput, actualOutput);
    }

    @Test
    void groupVariablesTest() throws JsonProcessingException {
        ApiResponse<AllTaskCaseInstanceDTO> actualOutput = groupController.groupVariables(id);
        ApiResponse<AllTaskCaseInstanceDTO> expectedOutput = new ApiResponse<>(groupService.groupVariables(id),true,MessageConstants. GET_CASE_INSTANCES);
        Assertions.assertEquals(expectedOutput, actualOutput);
    }

    @Test
    void groupCaseDefinitionTest() throws JsonProcessingException {
        ApiResponse<GroupTaskCaseDefinition> actualOutput = groupController.groupCaseDefinition(id);
        ApiResponse<GroupTaskCaseDefinition> expectedOutput = new ApiResponse<>(groupService.groupCaseDefinition(id),true,MessageConstants. GET_CASE_INSTANCES);
        Assertions.assertEquals(expectedOutput, actualOutput);
    }
}