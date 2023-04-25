package com.techsophy.tsf.wrapperservice.service.impl;

import com.fasterxml.jackson.core.JsonProcessingException;
import com.fasterxml.jackson.databind.ObjectMapper;
import com.techsophy.tsf.wrapperservice.config.UxControllerCamundaServletContextPath;
import com.techsophy.tsf.wrapperservice.config.GlobalMessageSource;
import com.techsophy.tsf.wrapperservice.dto.HistoryDataDTO;
import com.techsophy.tsf.wrapperservice.dto.HistoryDataResponseDTO;
import com.techsophy.tsf.wrapperservice.exception.InvalidInputException;
import com.techsophy.tsf.wrapperservice.service.HistoryDataService;
import com.techsophy.tsf.wrapperservice.utils.TokenUtils;
import com.techsophy.tsf.wrapperservice.utils.WebClientWrapper;
import lombok.AllArgsConstructor;
import org.apache.commons.lang3.StringUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.cloud.context.config.annotation.RefreshScope;
import org.springframework.stereotype.Service;
import org.springframework.web.reactive.function.client.WebClient;

import java.util.List;

import static com.techsophy.tsf.wrapperservice.constants.ApplicationConstants.CAMUNDA_SERVLET_CONTEXT_PATH_VARIABLE;
import static com.techsophy.tsf.wrapperservice.constants.ApplicationConstants.GATEWAY_URI_VARIABLE;
import static com.techsophy.tsf.wrapperservice.constants.CamundaApiConstants.*;
import static com.techsophy.tsf.wrapperservice.constants.ErrorConstants.UNABLE_TO_RETRIEVE_COUNT;

@RefreshScope
@Service
@AllArgsConstructor(onConstructor_ = {@Autowired})
public class HistoryDataServiceImpl implements HistoryDataService {

    WebClientWrapper webClientWrapper;
    TokenUtils accountUtils;
    ObjectMapper objectMapper;
    GlobalMessageSource globalMessageSource;
    private final UxControllerCamundaServletContextPath uxControllerCamundaServletContextPath;
    @Value(CAMUNDA_SERVLET_CONTEXT_PATH_VARIABLE)
    private String camundaServletContextPath;

    @Value(GATEWAY_URI_VARIABLE)
    private String gatewayURI;

    @Override
    public List<HistoryDataResponseDTO> historyData(HistoryDataDTO historyDataDTO, Integer firstResult, Integer maxResult) throws JsonProcessingException {

        String url = gatewayURI + uxControllerCamundaServletContextPath.getCamundaPathUri() + GET_TASK_DATA_HISTORY;
        WebClient webClient=webClientWrapper.createWebClient(accountUtils.getTokenFromContext());
        String response = webClientWrapper.webclientRequest(webClient,url+FIRSTRESULT+firstResult+MAXRESULT+maxResult,POST,historyDataDTO);
        if(StringUtils.isNotEmpty(response))
        {
            return  this.objectMapper.readValue(response,List.class);
        }
        throw new InvalidInputException(UNABLE_TO_RETRIEVE_COUNT,globalMessageSource.get(UNABLE_TO_RETRIEVE_COUNT));
    }
}
