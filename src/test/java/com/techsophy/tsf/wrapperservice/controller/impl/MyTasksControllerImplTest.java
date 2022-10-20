package com.techsophy.tsf.wrapperservice.controller.impl;

import com.fasterxml.jackson.core.JsonProcessingException;
import com.techsophy.tsf.wrapperservice.constants.MessageConstants;
import com.techsophy.tsf.wrapperservice.dto.ApiResponse;
import com.techsophy.tsf.wrapperservice.dto.MyTasksDTO;
import com.techsophy.tsf.wrapperservice.service.MyTasksService;
import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.Mockito;
import org.mockito.junit.jupiter.MockitoExtension;

import static org.junit.jupiter.api.Assertions.*;

@ExtendWith(MockitoExtension.class)
class MyTasksControllerImplTest {

    @Mock
    MyTasksService myTasksService;

    @InjectMocks
    MyTasksControllerImpl myTasksController;

    MyTasksDTO myTasksDTO;
    String id;

    @BeforeEach
    void setUp() {
        myTasksDTO = Mockito.mock(MyTasksDTO.class);
        id = "id";
    }

    @Test
    void myTasksCountTest() throws JsonProcessingException {
        ApiResponse actualOutput = myTasksController.myTasksCount(myTasksDTO);
        ApiResponse expectedOutput = new ApiResponse<>(myTasksService.myTasksCount(myTasksDTO), true, MessageConstants.GET_TASK_COUNT);
        Assertions.assertEquals(expectedOutput, actualOutput);
    }

    @Test
    void getAllMyTasksTest() throws JsonProcessingException {
        String firstResult = "1";
        String maxResult = "1";
        ApiResponse actualOutput = myTasksController.getAllMyTasks(myTasksDTO, firstResult, maxResult);
        ApiResponse expectedOutput = new ApiResponse<>(this.myTasksService.getAllTasks(myTasksDTO,firstResult,maxResult), true, MessageConstants.GET_ALL_TASKS_SUCCESS);
        Assertions.assertEquals(expectedOutput, actualOutput);
    }

    @Test
    void getMyTasksHistoryTest() throws JsonProcessingException {
        ApiResponse actualOutput = myTasksController.getMyTasksHistory(id);
        ApiResponse expectedOutput = new ApiResponse<>(myTasksService.getMyTasksHistory(id), true, MessageConstants.GET_MY_TASKS_HISTORY_SUCCESS);
        Assertions.assertEquals(expectedOutput, actualOutput);
    }

    @Test
    void getMyTasksByIdTest() throws JsonProcessingException {
        ApiResponse actualOutput = myTasksController.getMyTasksById(id);
        ApiResponse expectedOutput = new ApiResponse<>(myTasksService.getMyTasksById(id), true, MessageConstants.GET_MY_TASKS_SUCCESS);
        Assertions.assertEquals(expectedOutput, actualOutput);
    }
}