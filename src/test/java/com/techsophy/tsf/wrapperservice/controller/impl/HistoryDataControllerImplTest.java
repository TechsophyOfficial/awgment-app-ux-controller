package com.techsophy.tsf.wrapperservice.controller.impl;

import com.fasterxml.jackson.core.JsonProcessingException;
import com.techsophy.tsf.wrapperservice.constants.MessageConstants;
import com.techsophy.tsf.wrapperservice.dto.ApiResponse;
import com.techsophy.tsf.wrapperservice.dto.HistoryDataDTO;
import com.techsophy.tsf.wrapperservice.dto.HistoryDataResponseDTO;
import com.techsophy.tsf.wrapperservice.service.HistoryDataService;
import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.Mockito;
import org.mockito.junit.jupiter.MockitoExtension;

import java.util.List;

import static org.junit.jupiter.api.Assertions.*;

@ExtendWith(MockitoExtension.class)
class HistoryDataControllerImplTest {

    @Mock
    HistoryDataService historyDataService;
    @InjectMocks
    HistoryDataControllerImpl historyDataController;

    @Test
    void historyDataTest() throws JsonProcessingException {
        HistoryDataDTO dto = Mockito.mock(HistoryDataDTO.class);
        Integer firstResult = 1;
        Integer maxResult = 1;
        ApiResponse<List<HistoryDataResponseDTO>> actualOutput = historyDataController.historyData(dto, firstResult, maxResult);
        ApiResponse<List<HistoryDataResponseDTO>> expectedOutput = new ApiResponse<>(historyDataService.historyData(dto, firstResult, maxResult),
                true, MessageConstants.CREATE_TASK_COUNT_SUCCESS);
        Assertions.assertEquals(expectedOutput, actualOutput);
    }
}