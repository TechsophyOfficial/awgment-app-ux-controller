package com.techsophy.tsf.wrapperservice.service;

import com.fasterxml.jackson.core.JsonProcessingException;
import com.techsophy.tsf.wrapperservice.dto.HistoryDTO;
import com.techsophy.tsf.wrapperservice.dto.HistoryResponseDTO;

public interface HistoryService
{
    HistoryResponseDTO historyCount(HistoryDTO historyDTO) throws JsonProcessingException;
    /**
     * get history of a task
     * @param filter
     */

}
