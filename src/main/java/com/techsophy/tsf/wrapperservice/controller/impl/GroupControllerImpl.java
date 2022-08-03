package com.techsophy.tsf.wrapperservice.controller.impl;

import com.fasterxml.jackson.core.JsonProcessingException;
import com.techsophy.tsf.wrapperservice.constants.MessageConstants;
import com.techsophy.tsf.wrapperservice.controller.GroupControllerCount;
import com.techsophy.tsf.wrapperservice.dto.*;
import com.techsophy.tsf.wrapperservice.service.GroupService;
import lombok.AllArgsConstructor;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RestController;

import java.util.List;

@RestController
@AllArgsConstructor(onConstructor_ ={@Autowired} )
public class GroupControllerImpl implements GroupControllerCount {
    private GroupService groupService;
    @Override
    public ApiResponse<TaskCountDTO> groupTaskCount(@RequestBody GroupTaskCountReqDTO groupTaskCountReqDTO) throws JsonProcessingException {
        return new ApiResponse<>(this.groupService.groupCount(groupTaskCountReqDTO),true, MessageConstants.GET_TASK_COUNT_SUCCESS);
    }

    @Override
    public ApiResponse<List<GroupTaskDTO>> groupTask(GroupTaskCountReqDTO groupTaskDTO, Integer firstResult, Integer maxResults) throws JsonProcessingException {
        return new ApiResponse<>(this.groupService.groupTask(groupTaskDTO,firstResult,maxResults),true,MessageConstants.GET_GROUP_TASKS);
    }

    @Override
    public ApiResponse<List<GroupTaskHistoryDTO>> groupTaskHistory(String caseInstanceId) throws JsonProcessingException {
        return new ApiResponse<>(this.groupService.groupTaskHistory(caseInstanceId),true,MessageConstants. GET_CASE_INSTANCES);
    }

    @Override
    public ApiResponse<GroupCaseInstanceDTO> groupCaseInstance(String id) throws JsonProcessingException {
        return new ApiResponse<>(this.groupService.groupCaseInstance(id),true,MessageConstants. GET_CASE_INSTANCES);
    }

    @Override
    public ApiResponse<GroupFormDTo> groupFormVariables(String id) throws JsonProcessingException {
        return new ApiResponse<>(this.groupService.groupFormVariables(id),true,MessageConstants. GET_CASE_INSTANCES);
    }

    @Override
    public ApiResponse<AllTaskCaseInstanceDTO> groupVariables(String id) throws JsonProcessingException {
        return new ApiResponse<>(this.groupService.groupVariables(id),true,MessageConstants. GET_CASE_INSTANCES);
    }

    @Override
    public ApiResponse<GroupTaskCaseDefinition> groupCaseDefinition(String id) throws JsonProcessingException {
        return new ApiResponse<>(this.groupService.groupCaseDefinition(id),true,MessageConstants. GET_CASE_INSTANCES);
    }

}
