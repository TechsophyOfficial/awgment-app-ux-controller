package com.techsophy.tsf.wrapperservice.controller;

import com.fasterxml.jackson.core.JsonProcessingException;
import com.techsophy.tsf.wrapperservice.dto.ApiResponse;
import com.techsophy.tsf.wrapperservice.dto.HistoryDTO;
import com.techsophy.tsf.wrapperservice.dto.HistoryResponseDTO;
import org.springframework.security.access.prepost.PreAuthorize;
import org.springframework.web.bind.annotation.*;

import static com.techsophy.tsf.wrapperservice.constants.ApplicationEndpointConstants.*;
import static com.techsophy.tsf.wrapperservice.constants.MessageConstants.*;
import static com.techsophy.tsf.wrapperservice.constants.MessageConstants.TASK;

@RequestMapping(BASEURL+VERSION_1)
public interface HistoryController
{
    @PostMapping(TASK+COUNT)
    @PreAuthorize(CREATE_OR_ALL_ACCESS)
    ApiResponse<HistoryResponseDTO> historyCount(@RequestBody HistoryDTO historyDTO) throws JsonProcessingException;

}
