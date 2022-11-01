package com.techsophy.tsf.wrapperservice.controller.impl;

import com.fasterxml.jackson.core.JsonProcessingException;
import com.techsophy.tsf.wrapperservice.constants.MessageConstants;
import com.techsophy.tsf.wrapperservice.dto.*;
import com.techsophy.tsf.wrapperservice.service.AllTasksService;
import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.Mockito;
import org.mockito.junit.jupiter.MockitoExtension;
import org.springframework.test.util.ReflectionTestUtils;

import java.util.List;
import java.util.Map;

import static org.junit.jupiter.api.Assertions.*;

@ExtendWith(MockitoExtension.class)
class AllTasksControllerImplTest {

    @Mock
    AllTasksService allTasksService;

    @InjectMocks
    AllTasksControllerImpl allTasksController;

    String id;

    @BeforeEach
    void setUp() {
        id = "id";
    }

    @Test
    void allTasksCountTest() throws JsonProcessingException {
        AllTasksCountDTO allTasksCountDTO = Mockito.mock(AllTasksCountDTO.class);
        TaskCountDTO taskCountDTO = Mockito.mock(TaskCountDTO.class);
        Mockito.when(allTasksService.allTasksCount(allTasksCountDTO)).thenReturn(taskCountDTO);

        ApiResponse<TaskCountDTO> actualOutput = allTasksController.allTasksCount(allTasksCountDTO);
        ApiResponse<TaskCountDTO> expectedOutput = new ApiResponse<>(allTasksService.allTasksCount(allTasksCountDTO),true, MessageConstants.GET_TASK_COUNT_SUCCESS);

        Assertions.assertEquals(expectedOutput, actualOutput);
    }

    @Test
    void allTasksCountTestWithThreeParameter() throws JsonProcessingException {
        AllTasksCountDTO allTasksCountDTO = Mockito.mock(AllTasksCountDTO.class);
        AllTasksDTO allTasksDTO = Mockito.mock(AllTasksDTO.class);
        Mockito.when(allTasksService.allTasks(allTasksCountDTO, 1, 1)).thenReturn(List.of(allTasksDTO));

        ApiResponse<List<AllTasksDTO>> actualOutput = allTasksController.allTasksCount(allTasksCountDTO, 1, 1);
        ApiResponse<List<AllTasksDTO>> expectedOutput = new ApiResponse<>(allTasksService.allTasks(allTasksCountDTO, 1, 1),true,MessageConstants. GET_ALL_TASKS);

        Assertions.assertEquals(expectedOutput, actualOutput);
    }

    @Test
    void allTaskCaseInstanceTest() throws JsonProcessingException {
        AllTaskCaseInstanceDTO allTaskCaseInstanceDTO = Mockito.mock(AllTaskCaseInstanceDTO.class);
        Mockito.when(allTasksService.allTaskCaseInstance(id)).thenReturn(allTaskCaseInstanceDTO);

        ApiResponse<AllTaskCaseInstanceDTO> actualOutput = allTasksController.allTaskCaseInstance(id);
        ApiResponse<AllTaskCaseInstanceDTO> expectedOutput = new ApiResponse<>(allTasksService.allTaskCaseInstance(id),true,MessageConstants. GET_ALL_TASKS);

        Assertions.assertEquals(expectedOutput, actualOutput);
    }

    @Test
    void allTaskCaseActivityInstanceTest() throws JsonProcessingException {
        CaseActivityInstanceDTO caseActivityInstanceDTO = Mockito.mock(CaseActivityInstanceDTO.class);
        Mockito.when(allTasksService.allTaskCaseActivityInstance(id)).thenReturn(List.of(caseActivityInstanceDTO));

        ApiResponse<List<CaseActivityInstanceDTO>> actualOutput = allTasksController.allTaskCaseActivityInstance(id);
        ApiResponse<List<CaseActivityInstanceDTO>> expectedOutput = new ApiResponse<>(allTasksService.allTaskCaseActivityInstance(id),true,MessageConstants. GET_ALL_TASKS);

        Assertions.assertEquals(expectedOutput, actualOutput);
    }

    @Test
    void allTaskVariablesTest() throws JsonProcessingException {
        AllTaskFormsDTO allTaskFormsDTO = Mockito.mock(AllTaskFormsDTO.class);
        Mockito.when(allTasksService.allTaskVariables(id)).thenReturn(allTaskFormsDTO);

        ApiResponse<AllTaskFormsDTO> actualOutput = allTasksController.allTaskVariables(id);
        ApiResponse<AllTaskFormsDTO> expectedOutput = new ApiResponse<>(allTasksService.allTaskVariables(id),true,MessageConstants. GET_ALL_TASKS);

        Assertions.assertEquals(expectedOutput, actualOutput);
    }

    @Test
    void allTaskFormVariablesTest() throws JsonProcessingException {
        AllTaskFormVariablesDTO allTaskFormVariablesDTO = Mockito.mock(AllTaskFormVariablesDTO.class);
        Mockito.when(allTasksService.allTaskFormVariables(id)).thenReturn(allTaskFormVariablesDTO);

        ApiResponse<AllTaskFormVariablesDTO> actualOutput = allTasksController.allTaskFormVariables(id);
        ApiResponse<AllTaskFormVariablesDTO> expectedOutput = new ApiResponse<>(allTasksService.allTaskFormVariables(id),true,MessageConstants. GET_ALL_TASKS);

        Assertions.assertEquals(expectedOutput, actualOutput);
    }
}