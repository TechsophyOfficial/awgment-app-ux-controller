package com.techsophy.tsf.wrapperservice.controller;

import com.fasterxml.jackson.core.JsonProcessingException;
import com.techsophy.tsf.wrapperservice.dto.*;
import org.springframework.security.access.prepost.PreAuthorize;
import org.springframework.web.bind.annotation.*;

import java.util.List;

import static com.techsophy.tsf.wrapperservice.constants.ApplicationConstants.VERSION_V1;
import static com.techsophy.tsf.wrapperservice.constants.ApplicationEndpointConstants.*;
import static com.techsophy.tsf.wrapperservice.constants.CamundaApiConstants.*;
import static com.techsophy.tsf.wrapperservice.constants.CamundaApiConstants.CASEINSTANCE;
import static com.techsophy.tsf.wrapperservice.constants.MessageConstants.*;
import static com.techsophy.tsf.wrapperservice.constants.MessageConstants.ID;

@RequestMapping(BASEURL+VERSION_V1)
public interface AllTasksController {

    @PostMapping(ALLTASKS+COUNT)
    @PreAuthorize(CREATE_OR_ALL_ACCESS)
    ApiResponse<TaskCountDTO> allTasksCount(@RequestBody AllTasksCountDTO allTasksCountDTO) throws JsonProcessingException;

    @PostMapping(ALLTASKS)
    @PreAuthorize(CREATE_OR_ALL_ACCESS)
    ApiResponse<List<AllTasksDTO>> allTasksCount(@RequestBody AllTasksCountDTO allTasksCountDTO,
                                                 @RequestParam(name=FIRST_RESULT,required = false,defaultValue = "0")Integer firstResult,
                                                 @RequestParam(name=MAX_RESULTS,required = false,defaultValue = "15")Integer maxResults) throws JsonProcessingException;

    @GetMapping(ALLTASKS+ CASEINSTANCE)
    @PreAuthorize(READ_OR_ALL_ACCESS)
    ApiResponse<AllTaskCaseInstanceDTO> allTaskCaseInstance(@PathVariable(ID) String id) throws JsonProcessingException;

    @GetMapping(ALLTASKS+ CASEACTIVITYINSTANCE)
    @PreAuthorize(READ_OR_ALL_ACCESS)
    ApiResponse<List<CaseActivityInstanceDTO>> allTaskCaseActivityInstance(@RequestParam(name=CASEINSTANCEID) String caseInstanceId) throws JsonProcessingException;

    @GetMapping(ALLTASKS+ VARIABLE)
    @PreAuthorize(READ_OR_ALL_ACCESS)
    ApiResponse<AllTaskFormsDTO> allTaskVariables(@PathVariable(ID) String id) throws JsonProcessingException;

    @GetMapping(ALLTASKS+ ALLTASK_FORM_VARIABLE)
    @PreAuthorize(READ_OR_ALL_ACCESS)
    ApiResponse<AllTaskFormVariablesDTO> allTaskFormVariables(@PathVariable(ID) String id) throws JsonProcessingException;


}
