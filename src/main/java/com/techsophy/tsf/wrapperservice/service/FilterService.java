package com.techsophy.tsf.wrapperservice.service;

import com.fasterxml.jackson.core.JsonProcessingException;
import com.techsophy.tsf.wrapperservice.dto.*;

import java.util.List;
import java.util.Map;

public interface FilterService
{
    FilterResponseDTO createFilter(FilterDTO createFilterDTO) throws JsonProcessingException;
    List<Map<String,Object>> executeFilter(String id, ExecuteFilterDTO executeFilterDTO, String emptyString, String firstResult, String maxResult,boolean involvedUser) throws JsonProcessingException;
    FilterResponseDTO getFilterById(String id) throws JsonProcessingException;
    FilterCountResponseDTO filterCount(String id,FilterCountDTO filterCountDTO) throws JsonProcessingException;
    Map<String,Object> getFormVariables(String id) throws JsonProcessingException;
}
