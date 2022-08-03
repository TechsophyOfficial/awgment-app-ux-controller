package com.techsophy.tsf.wrapperservice.controller.impl;

import com.fasterxml.jackson.core.JsonProcessingException;
import com.techsophy.tsf.wrapperservice.constants.MessageConstants;
import com.techsophy.tsf.wrapperservice.controller.HistoryDataController;
import com.techsophy.tsf.wrapperservice.dto.ApiResponse;
import com.techsophy.tsf.wrapperservice.dto.HistoryDataDTO;
import com.techsophy.tsf.wrapperservice.dto.HistoryDataResponseDTO;
import com.techsophy.tsf.wrapperservice.service.HistoryDataService;
import com.techsophy.tsf.wrapperservice.service.impl.HistoryDataServiceImpl;
import lombok.AllArgsConstructor;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.RestController;

import java.util.List;

@RestController
@AllArgsConstructor(onConstructor_={@Autowired})
public class HistoryDataControllerImpl implements HistoryDataController {
    private final HistoryDataService historyDataService;

    @Override
    public ApiResponse<List<HistoryDataResponseDTO>> historyData(HistoryDataDTO historyDataDTO, Integer firstResult, Integer maxResults) throws JsonProcessingException {
        return new ApiResponse<>(this.historyDataService.historyData(historyDataDTO,firstResult,maxResults), true, MessageConstants.CREATE_TASK_COUNT_SUCCESS);
    }
}
