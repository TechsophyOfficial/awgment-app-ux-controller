package com.techsophy.tsf.wrapperservice.controller.impl;

import com.fasterxml.jackson.core.JsonProcessingException;
import com.techsophy.tsf.wrapperservice.constants.MessageConstants;
import com.techsophy.tsf.wrapperservice.controller.FilterController;
import com.techsophy.tsf.wrapperservice.dto.*;
import com.techsophy.tsf.wrapperservice.service.FilterService;
import lombok.AllArgsConstructor;
import org.springframework.web.bind.annotation.RestController;

@RestController
@AllArgsConstructor
public class FilterControllerImpl implements FilterController
{
    private final FilterService filterService;

    @Override
    public ApiResponse<FilterResponseDTO> createFilter(FilterDTO createFilterDTO) throws JsonProcessingException {
        return new ApiResponse<>(this.filterService.createFilter(createFilterDTO), true, MessageConstants.CREATE_FILTER_SUCCESS);
    }

    @Override
    public ApiResponse executeFilter(String id, ExecuteFilterDTO executeFilterDTO, String emptyString, String firstResult, String maxResult, boolean involvedUser) throws JsonProcessingException {
        return new ApiResponse<>(this.filterService.executeFilter(id,executeFilterDTO,emptyString,firstResult,maxResult,involvedUser), true, MessageConstants.EXECUTE_FILTER_SUCCESS);
    }
    @Override
    public ApiResponse<FilterResponseDTO> getFilterById(String id) throws JsonProcessingException
    {
        return new ApiResponse<>(this.filterService.getFilterById(id), true, MessageConstants.GET_FILTER_SUCCESS);

    }

    @Override
    public ApiResponse FilterCount(String id,FilterCountDTO filterCountDTO) throws JsonProcessingException
    {
        return new ApiResponse<>(this.filterService.filterCount(id,filterCountDTO), true, MessageConstants.GET_FILTER_COUNT_SUCCESS);

    }

    @Override
    public ApiResponse getFormVariables(String id) throws JsonProcessingException {
        return new ApiResponse<>(this.filterService.getFormVariables(id), true, MessageConstants.GET_FILTER_FORM_VARIABLES);

    }

}
