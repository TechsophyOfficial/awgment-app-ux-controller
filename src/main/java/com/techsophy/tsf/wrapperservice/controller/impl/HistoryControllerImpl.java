package com.techsophy.tsf.wrapperservice.controller.impl;

import com.fasterxml.jackson.core.JsonProcessingException;
import com.techsophy.tsf.wrapperservice.constants.MessageConstants;
import com.techsophy.tsf.wrapperservice.controller.HistoryController;
import com.techsophy.tsf.wrapperservice.dto.ApiResponse;
import com.techsophy.tsf.wrapperservice.dto.HistoryDTO;
import com.techsophy.tsf.wrapperservice.dto.HistoryResponseDTO;
import com.techsophy.tsf.wrapperservice.service.HistoryService;
import lombok.AllArgsConstructor;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.RestController;

import java.util.List;

@RestController
@AllArgsConstructor(onConstructor_ = {@Autowired})
public class HistoryControllerImpl  implements HistoryController {
 private final HistoryService historyService;
    @Override
    public ApiResponse<HistoryResponseDTO> historyCount(HistoryDTO historyDTO) throws JsonProcessingException {

        return new ApiResponse<>(this.historyService.historyCount(historyDTO), true, MessageConstants.CREATE_TASK_COUNT_SUCCESS);
    }


}
