package com.techsophy.tsf.wrapperservice.controller;

import com.fasterxml.jackson.core.JsonProcessingException;
import com.techsophy.tsf.wrapperservice.constants.ApplicationEndpointConstants;
import com.techsophy.tsf.wrapperservice.constants.MessageConstants;
import com.techsophy.tsf.wrapperservice.dto.ApiResponse;
import com.techsophy.tsf.wrapperservice.dto.MyTasksDTO;
import org.springframework.security.access.prepost.PreAuthorize;
import org.springframework.web.bind.annotation.*;

import static com.techsophy.tsf.wrapperservice.constants.ApplicationEndpointConstants.*;
import static com.techsophy.tsf.wrapperservice.constants.CamundaApiConstants.*;
import static com.techsophy.tsf.wrapperservice.constants.MessageConstants.*;

@RequestMapping(BASEURL + VERSION_1)
public interface MyTasksController
{
    @PostMapping(ApplicationEndpointConstants.MY_TASKS+ MessageConstants.COUNT)
    @PreAuthorize(CREATE_OR_ALL_ACCESS)
    ApiResponse  myTasksCount(@RequestBody MyTasksDTO myTasksDTO) throws JsonProcessingException;

    @PostMapping(ApplicationEndpointConstants.MY_TASKS)
    @PreAuthorize(CREATE_OR_ALL_ACCESS)
    ApiResponse getAllMyTasks(@RequestBody MyTasksDTO myTasksDTO,
                              @RequestParam(value = FIRST_RESULT, required = false) String firstResult,
                              @RequestParam(value = MAX_RESULTS, required = false) String maxResult) throws JsonProcessingException;

    @GetMapping(ApplicationEndpointConstants.MY_TASKS+ HISTORY+CASE_ACTIVITY_INSTANCE)
    @PreAuthorize(READ_OR_ALL_ACCESS)
    ApiResponse  getMyTasksHistory(@RequestParam(value=CASE_INSTANCEID) String caseInstanceId) throws JsonProcessingException;

    @GetMapping(ApplicationEndpointConstants.MY_TASKS+ApplicationEndpointConstants.ID)
    @PreAuthorize(READ_OR_ALL_ACCESS)
    ApiResponse  getMyTasksById(@PathVariable(MessageConstants.ID) String id) throws JsonProcessingException;

}
