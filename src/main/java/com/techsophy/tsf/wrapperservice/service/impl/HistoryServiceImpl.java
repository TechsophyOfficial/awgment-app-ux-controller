package com.techsophy.tsf.wrapperservice.service.impl;

import com.fasterxml.jackson.core.JsonProcessingException;
import com.fasterxml.jackson.databind.ObjectMapper;
import com.techsophy.tsf.wrapperservice.config.CamundaModifiedPathURL;
import com.techsophy.tsf.wrapperservice.config.GlobalMessageSource;
import com.techsophy.tsf.wrapperservice.dto.HistoryDTO;
import com.techsophy.tsf.wrapperservice.dto.HistoryResponseDTO;
import com.techsophy.tsf.wrapperservice.exception.InvalidInputException;
import com.techsophy.tsf.wrapperservice.service.HistoryService;
import com.techsophy.tsf.wrapperservice.utils.TokenUtils;
import com.techsophy.tsf.wrapperservice.utils.WebClientWrapper;
import lombok.AllArgsConstructor;
import org.apache.commons.lang3.StringUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.cloud.context.config.annotation.RefreshScope;
import org.springframework.stereotype.Service;
import org.springframework.web.reactive.function.client.WebClient;

import static com.techsophy.tsf.wrapperservice.constants.ApplicationConstants.CAMUNDA_SERVLET_CONTEXT_PATH_VARIABLE;
import static com.techsophy.tsf.wrapperservice.constants.ApplicationConstants.GATEWAY_URI_VARIABLE;
import static com.techsophy.tsf.wrapperservice.constants.CamundaApiConstants.GET_TASK_COUNT_HISTORY;
import static com.techsophy.tsf.wrapperservice.constants.CamundaApiConstants.POST;
import static com.techsophy.tsf.wrapperservice.constants.ErrorConstants.UNABLE_TO_RETRIEVE_COUNT;


@RefreshScope
@Service
@AllArgsConstructor(onConstructor_ = {@Autowired})
public class HistoryServiceImpl implements HistoryService
{
    WebClientWrapper webClientWrapper;
    TokenUtils accountUtils;
    ObjectMapper objectMapper;
    GlobalMessageSource globalMessageSource;
    private final CamundaModifiedPathURL camundaModifiedPathURL;
    @Value(CAMUNDA_SERVLET_CONTEXT_PATH_VARIABLE)
    private String camundaServletContextPath;

    @Value(GATEWAY_URI_VARIABLE)
    private String gatewayURI;

    @Override
    public HistoryResponseDTO historyCount(HistoryDTO historyDTO) throws JsonProcessingException {
        String url = camundaModifiedPathURL.getCamundaPathUri(GET_TASK_COUNT_HISTORY);
        WebClient webClient=webClientWrapper.createWebClient(accountUtils.getTokenFromContext());
        String response = webClientWrapper.webclientRequest(webClient,url,POST,historyDTO);
        if(StringUtils.isNotEmpty(response))
        {
            return this.objectMapper.readValue(response,HistoryResponseDTO.class);
        }
         throw new InvalidInputException(UNABLE_TO_RETRIEVE_COUNT,globalMessageSource.get(UNABLE_TO_RETRIEVE_COUNT));
    }


}
