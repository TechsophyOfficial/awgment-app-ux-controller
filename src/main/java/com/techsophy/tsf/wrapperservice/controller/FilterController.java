package com.techsophy.tsf.wrapperservice.controller;

import com.fasterxml.jackson.core.JsonProcessingException;
import com.techsophy.tsf.wrapperservice.constants.ApplicationEndpointConstants;
import com.techsophy.tsf.wrapperservice.constants.MessageConstants;
import com.techsophy.tsf.wrapperservice.dto.*;
import org.springframework.security.access.prepost.PreAuthorize;
import org.springframework.web.bind.annotation.*;

import static com.techsophy.tsf.wrapperservice.constants.ApplicationEndpointConstants.*;
import static com.techsophy.tsf.wrapperservice.constants.CamundaApiConstants.*;

@RequestMapping(ApplicationEndpointConstants.BASEURL + ApplicationEndpointConstants.VERSION_1)
public interface FilterController
{
    @PostMapping(FILTER +CREATE)
    @PreAuthorize(CREATE_OR_ALL_ACCESS)
    ApiResponse<FilterResponseDTO> createFilter(@RequestBody FilterDTO createFilterDTO) throws JsonProcessingException;

    @PostMapping(FILTER+EXECUTE_LIST)
    @PreAuthorize(CREATE_OR_ALL_ACCESS)
    ApiResponse executeFilter(@PathVariable(FILTER_ID)String id, @RequestBody ExecuteFilterDTO executeFilterDTO,
                              @RequestParam(value ="", required = false,defaultValue = "") String emptyString,
                              @RequestParam(value = FIRST_RESULT, required = false,defaultValue = "0") String firstResult,
                              @RequestParam(value = MAX_RESULTS, required = false,defaultValue = "15") String maxResult,
                              @RequestParam(value = INVOLVED_USER, required = false) boolean involvedUser
    ) throws JsonProcessingException;
    @GetMapping(FILTER+ID)
    @PreAuthorize(READ_OR_ALL_ACCESS)
    ApiResponse<FilterResponseDTO> getFilterById(@PathVariable(FILTER_ID) String id) throws JsonProcessingException;

    @PostMapping(FILTER+ID+MessageConstants.COUNT)
    @PreAuthorize(CREATE_OR_ALL_ACCESS)
    ApiResponse  FilterCount(@PathVariable(FILTER_ID) String id,@RequestBody FilterCountDTO filterCountDTO) throws JsonProcessingException;

    @GetMapping(ID+FORM_VARIABLES)
    @PreAuthorize(READ_OR_ALL_ACCESS)
    ApiResponse getFormVariables(@PathVariable(FILTER_ID) String id) throws JsonProcessingException;



}
