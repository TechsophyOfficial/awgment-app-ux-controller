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

        ApiResponse<TaskCountDTO> actualOutput = allTasksController.allTasksCount(allTasksCountDTO);
        ApiResponse<TaskCountDTO> expectedOutput = new ApiResponse<>(allTasksService.allTasksCount(allTasksCountDTO),true, MessageConstants.GET_TASK_COUNT_SUCCESS);

        Assertions.assertEquals(expectedOutput, actualOutput);
    }

    @Test
    void allTasksCountTestWithThreeParameter() throws JsonProcessingException {
        AllTasksCountDTO allTasksCountDTO = Mockito.mock(AllTasksCountDTO.class);

        ApiResponse<List<AllTasksDTO>> actualOutput = allTasksController.allTasksCount(allTasksCountDTO, 1, 1);
        ApiResponse<List<AllTasksDTO>> expectedOutput = new ApiResponse<>(allTasksService.allTasks(allTasksCountDTO, 1, 1),true,MessageConstants. GET_ALL_TASKS);

        Assertions.assertEquals(expectedOutput, actualOutput);
    }

    @Test
    void allTaskCaseInstanceTest() throws JsonProcessingException {
        ApiResponse<AllTaskCaseInstanceDTO> actualOutput = allTasksController.allTaskCaseInstance(id);
        ApiResponse<AllTaskCaseInstanceDTO> expectedOutput = new ApiResponse<>(allTasksService.allTaskCaseInstance(id),true,MessageConstants. GET_ALL_TASKS);

        Assertions.assertEquals(expectedOutput, actualOutput);
    }

    @Test
    void allTaskCaseActivityInstanceTest() throws JsonProcessingException {
        ApiResponse<List<CaseActivityInstanceDTO>> actualOutput = allTasksController.allTaskCaseActivityInstance(id);
        ApiResponse<List<CaseActivityInstanceDTO>> expectedOutput = new ApiResponse<>(allTasksService.allTaskCaseActivityInstance(id),true,MessageConstants. GET_ALL_TASKS);

        Assertions.assertEquals(expectedOutput, actualOutput);
    }

    @Test
    void allTaskVariablesTest() throws JsonProcessingException {
        ApiResponse<AllTaskFormsDTO> actualOutput = allTasksController.allTaskVariables(id);
        ApiResponse<AllTaskFormsDTO> expectedOutput = new ApiResponse<>(allTasksService.allTaskVariables(id),true,MessageConstants. GET_ALL_TASKS);

        Assertions.assertEquals(expectedOutput, actualOutput);
    }

    @Test
    void allTaskFormVariablesTest() throws JsonProcessingException {
        ApiResponse<AllTaskFormVariablesDTO> actualOutput = allTasksController.allTaskFormVariables(id);
        ApiResponse<AllTaskFormVariablesDTO> expectedOutput = new ApiResponse<>(allTasksService.allTaskFormVariables(id),true,MessageConstants. GET_ALL_TASKS);

        Assertions.assertEquals(expectedOutput, actualOutput);
    }
}