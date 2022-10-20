package com.techsophy.tsf.wrapperservice.controller.impl;

import com.fasterxml.jackson.core.JsonProcessingException;
import com.techsophy.tsf.wrapperservice.constants.MessageConstants;
import com.techsophy.tsf.wrapperservice.dto.ApiResponse;
import com.techsophy.tsf.wrapperservice.dto.HistoryDTO;
import com.techsophy.tsf.wrapperservice.dto.HistoryResponseDTO;
import com.techsophy.tsf.wrapperservice.service.HistoryService;
import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.Mockito;
import org.mockito.junit.jupiter.MockitoExtension;

import static org.junit.jupiter.api.Assertions.*;

@ExtendWith(MockitoExtension.class)
class HistoryControllerImplTest {
    @Mock
    HistoryService historyService;

    @InjectMocks
    HistoryControllerImpl historyController;

    @Test
    void historyCountTest() throws JsonProcessingException {
        HistoryDTO historyDTO = Mockito.mock(HistoryDTO.class);
        ApiResponse<HistoryResponseDTO> actualOutput = historyController.historyCount(historyDTO);
        ApiResponse<HistoryResponseDTO> expectedOutput = new ApiResponse<>(historyService.historyCount(historyDTO), true, MessageConstants.CREATE_TASK_COUNT_SUCCESS);
        Assertions.assertEquals(expectedOutput, actualOutput);
    }
}