package com.techsophy.tsf.wrapperservice.service;

import com.fasterxml.jackson.core.JsonProcessingException;
import com.techsophy.tsf.wrapperservice.dto.HistoryDataDTO;
import com.techsophy.tsf.wrapperservice.dto.HistoryDataResponseDTO;

import java.util.List;

public interface HistoryDataService {

    List<HistoryDataResponseDTO> historyData(HistoryDataDTO historyDataDTO, Integer firstResult, Integer maxResult) throws JsonProcessingException;
}
