package com.techsophy.tsf.wrapperservice.controller;

import com.fasterxml.jackson.core.JsonProcessingException;
import com.techsophy.tsf.wrapperservice.constants.ApplicationEndpointConstants;
import com.techsophy.tsf.wrapperservice.dto.*;
import org.springframework.security.access.prepost.PreAuthorize;
import org.springframework.web.bind.annotation.*;
import java.util.List;
import static com.techsophy.tsf.wrapperservice.constants.ApplicationConstants.CASEINSTANCEID;
import static com.techsophy.tsf.wrapperservice.constants.ApplicationEndpointConstants.CREATE_OR_ALL_ACCESS;
import static com.techsophy.tsf.wrapperservice.constants.ApplicationEndpointConstants.READ_OR_ALL_ACCESS;
import static com.techsophy.tsf.wrapperservice.constants.CamundaApiConstants.*;
import static com.techsophy.tsf.wrapperservice.constants.CamundaApiConstants.CASEINSTANCE;
import static com.techsophy.tsf.wrapperservice.constants.MessageConstants.*;

@RequestMapping(ApplicationEndpointConstants.BASEURL + ApplicationEndpointConstants.VERSION_1)
public interface GroupControllerCount {

    @PostMapping(GROUPTASK+COUNT)
    @PreAuthorize(CREATE_OR_ALL_ACCESS)
    ApiResponse<TaskCountDTO> groupTaskCount(GroupTaskCountReqDTO groupTaskCountReqDTO) throws JsonProcessingException;

    @PostMapping(GROUPTASK)
    @PreAuthorize(CREATE_OR_ALL_ACCESS)
    ApiResponse<List<GroupTaskDTO>> groupTask(@RequestBody GroupTaskCountReqDTO groupTaskDTO,
                                              @RequestParam(name = FIRST_RESULT,required = false,defaultValue = "0") Integer firstResult,
                                              @RequestParam (name = MAX_RESULTS,required = false,defaultValue = "15")Integer maxResults) throws JsonProcessingException;

    @GetMapping(GROUP+HISTORY)
    @PreAuthorize(READ_OR_ALL_ACCESS)
    ApiResponse<List<GroupTaskHistoryDTO>> groupTaskHistory(@RequestParam(name=CASEINSTANCEID, required=false,defaultValue = "922fef65-db59-11ec-8288-fe875f1a83e1")String caseInstanceId) throws JsonProcessingException;

    @GetMapping(GROUP+ CASEINSTANCE)
    @PreAuthorize(READ_OR_ALL_ACCESS)
    ApiResponse<GroupCaseInstanceDTO> groupCaseInstance(@PathVariable(ID) String id) throws JsonProcessingException;

    @GetMapping(GROUP+ FORM)
    @PreAuthorize(READ_OR_ALL_ACCESS)
    ApiResponse<GroupFormDTo> groupFormVariables(@PathVariable(ID) String id) throws JsonProcessingException;

    @GetMapping(GROUP+ VARIABLE)
    @PreAuthorize(READ_OR_ALL_ACCESS)
    ApiResponse<AllTaskCaseInstanceDTO> groupVariables(@PathVariable(ID) String id) throws JsonProcessingException;

    @GetMapping(GROUP+ CASE_DEFINITION)
    @PreAuthorize(READ_OR_ALL_ACCESS)
    ApiResponse<GroupTaskCaseDefinition> groupCaseDefinition(@PathVariable(ID) String id) throws JsonProcessingException;

}
