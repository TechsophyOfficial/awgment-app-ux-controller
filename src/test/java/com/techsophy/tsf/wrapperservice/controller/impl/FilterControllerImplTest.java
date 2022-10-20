package com.techsophy.tsf.wrapperservice.controller.impl;

import com.fasterxml.jackson.core.JsonProcessingException;
import com.techsophy.tsf.wrapperservice.constants.MessageConstants;
import com.techsophy.tsf.wrapperservice.dto.*;
import com.techsophy.tsf.wrapperservice.service.FilterService;
import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.Mockito;
import org.mockito.junit.jupiter.MockitoExtension;
import org.springframework.test.util.ReflectionTestUtils;

import java.util.List;
import java.util.Map;

import static org.junit.jupiter.api.Assertions.*;

@ExtendWith(MockitoExtension.class)
class FilterControllerImplTest {

    @Mock
    FilterService filterService;
    @InjectMocks
    FilterControllerImpl filterController;

    String id;

    @BeforeEach
    void setUp() {
        id = "id";
    }

    @Test
    void createFilterTest() throws JsonProcessingException {
        FilterDTO filterDTO = Mockito.mock(FilterDTO.class);
        ApiResponse<FilterResponseDTO> actualOutput = filterController.createFilter(filterDTO);
        ApiResponse<FilterResponseDTO> expectedOutput = new ApiResponse<>(filterService.createFilter(filterDTO), true, MessageConstants.CREATE_FILTER_SUCCESS);
        Assertions.assertEquals(expectedOutput, actualOutput);
    }

    @Test
    void executeFilterTest() throws JsonProcessingException {
        ExecuteFilterDTO executeFilterDTO = Mockito.mock(ExecuteFilterDTO.class);
        ApiResponse actualOutput = filterController.executeFilter(id, executeFilterDTO, "", "f_result", "m_result", true);
        ApiResponse expectedOutput = new ApiResponse<>(filterService.executeFilter(id, executeFilterDTO,"", "f_result", "m_result", true), true, MessageConstants.EXECUTE_FILTER_SUCCESS);
        Assertions.assertEquals(expectedOutput, actualOutput);
    }

    @Test
    void getFilterByIdTest() throws JsonProcessingException {
        ApiResponse<FilterResponseDTO> actualOutput = filterController.getFilterById(id);
        ApiResponse<FilterResponseDTO> expectedOutput = new ApiResponse<>(this.filterService.getFilterById(id), true, MessageConstants.GET_FILTER_SUCCESS);
        Assertions.assertEquals(expectedOutput, actualOutput);
    }

    @Test
    void filterCountTest() throws JsonProcessingException {
        FilterCountDTO filterCountDTO = Mockito.mock(FilterCountDTO.class);
        ApiResponse actualOutput = filterController.FilterCount(id, filterCountDTO);
        ApiResponse expectedOutput = new ApiResponse<>(filterService.filterCount(id,filterCountDTO), true, MessageConstants.GET_FILTER_COUNT_SUCCESS);
        Assertions.assertEquals(expectedOutput, actualOutput);
    }

    @Test
    void getFormVariablesTest() throws JsonProcessingException {
        ApiResponse actualOutput = filterController.getFormVariables(id);
        ApiResponse expectedOutput = new ApiResponse<>(filterService.getFormVariables(id), true, MessageConstants.GET_FILTER_FORM_VARIABLES);
        Assertions.assertEquals(expectedOutput, actualOutput);
    }
}