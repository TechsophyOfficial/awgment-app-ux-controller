package com.techsophy.tsf.wrapperservice.controller;

import com.fasterxml.jackson.core.JsonProcessingException;
import com.techsophy.tsf.wrapperservice.dto.*;
import org.springframework.security.access.prepost.PreAuthorize;
import org.springframework.web.bind.annotation.*;

import java.util.List;

import static com.techsophy.tsf.wrapperservice.constants.ApplicationEndpointConstants.*;
import static com.techsophy.tsf.wrapperservice.constants.MessageConstants.*;
import static com.techsophy.tsf.wrapperservice.constants.MessageConstants.TASK;

@RequestMapping(BASEURL+VERSION_1)
public interface HistoryDataController {

    @PostMapping(TASK)
    @PreAuthorize(CREATE_OR_ALL_ACCESS)
    ApiResponse<List<HistoryDataResponseDTO>> historyData(@RequestBody HistoryDataDTO historyDataDTO,@RequestParam(required = false, defaultValue = "0") Integer firstResult, @RequestParam(required = false, defaultValue = "15") Integer maxResults) throws JsonProcessingException;


}
