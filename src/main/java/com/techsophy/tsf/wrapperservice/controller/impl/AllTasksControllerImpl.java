package com.techsophy.tsf.wrapperservice.controller.impl;

import com.fasterxml.jackson.core.JsonProcessingException;
import com.techsophy.tsf.wrapperservice.constants.MessageConstants;
import com.techsophy.tsf.wrapperservice.controller.AllTasksController;
import com.techsophy.tsf.wrapperservice.dto.*;
import com.techsophy.tsf.wrapperservice.service.AllTasksService;
import lombok.AllArgsConstructor;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.RestController;

import java.util.List;

@RestController
@AllArgsConstructor(onConstructor_ ={@Autowired} )
public class AllTasksControllerImpl implements AllTasksController {
    private AllTasksService allTasksService;
    @Override
    public ApiResponse<TaskCountDTO> allTasksCount(AllTasksCountDTO allTasksCountDTO) throws JsonProcessingException {
        return new ApiResponse<>(this.allTasksService.allTasksCount(allTasksCountDTO),true, MessageConstants.GET_TASK_COUNT_SUCCESS);
    }

    @Override
    public ApiResponse<List<AllTasksDTO>> allTasksCount(AllTasksCountDTO allTasksCountDTO, Integer firstResult, Integer maxResults) throws JsonProcessingException {
        return new ApiResponse<>(this.allTasksService.allTasks(allTasksCountDTO,firstResult,maxResults),true,MessageConstants. GET_ALL_TASKS);
    }

    @Override
    public ApiResponse<AllTaskCaseInstanceDTO> allTaskCaseInstance(String id) throws JsonProcessingException {
        return new ApiResponse<>(this.allTasksService.allTaskCaseInstance(id),true,MessageConstants. GET_ALL_TASKS);
    }

    @Override
    public ApiResponse<List<CaseActivityInstanceDTO>> allTaskCaseActivityInstance(String caseInstanceId) throws JsonProcessingException {
        return new ApiResponse<>(this.allTasksService.allTaskCaseActivityInstance(caseInstanceId),true,MessageConstants. GET_ALL_TASKS);
    }

    @Override
    public ApiResponse<AllTaskFormsDTO> allTaskVariables(String id) throws JsonProcessingException {
        return new ApiResponse<>(this.allTasksService.allTaskVariables(id),true,MessageConstants. GET_ALL_TASKS);
    }

    @Override
    public ApiResponse<AllTaskFormVariablesDTO> allTaskFormVariables(String id) throws JsonProcessingException {
        return new ApiResponse<>(this.allTasksService.allTaskFormVariables(id),true,MessageConstants. GET_ALL_TASKS);
    }
}
