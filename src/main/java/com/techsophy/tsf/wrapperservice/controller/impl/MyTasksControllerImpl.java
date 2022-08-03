package com.techsophy.tsf.wrapperservice.controller.impl;

import com.fasterxml.jackson.core.JsonProcessingException;
import com.techsophy.tsf.wrapperservice.constants.MessageConstants;
import com.techsophy.tsf.wrapperservice.controller.MyTasksController;
import com.techsophy.tsf.wrapperservice.dto.ApiResponse;
import com.techsophy.tsf.wrapperservice.dto.MyTasksDTO;
import com.techsophy.tsf.wrapperservice.service.MyTasksService;
import lombok.AllArgsConstructor;
import org.springframework.web.bind.annotation.RestController;

@RestController
@AllArgsConstructor
public class MyTasksControllerImpl implements MyTasksController
{
    private final MyTasksService myTasksService;


    @Override
    public ApiResponse myTasksCount(MyTasksDTO myTasksDTO) throws JsonProcessingException {
        return new ApiResponse<>(this.myTasksService.myTasksCount(myTasksDTO), true, MessageConstants.GET_TASK_COUNT);
    }

    @Override
    public ApiResponse getAllMyTasks(MyTasksDTO myTasksDTO, String firstResult, String maxResult) throws JsonProcessingException {
        return new ApiResponse<>(this.myTasksService.getAllTasks(myTasksDTO,firstResult,maxResult), true, MessageConstants.GET_ALL_TASKS_SUCCESS);

    }

    @Override
    public ApiResponse getMyTasksHistory(String caseInstanceId) throws JsonProcessingException
    {
        return new ApiResponse<>(this.myTasksService.getMyTasksHistory(caseInstanceId), true, MessageConstants.GET_MY_TASKS_HISTORY_SUCCESS);

    }

    @Override
    public ApiResponse getMyTasksById(String id) throws JsonProcessingException
    {
        return new ApiResponse<>(this.myTasksService.getMyTasksById(id), true, MessageConstants.GET_MY_TASKS_SUCCESS);

    }
}
