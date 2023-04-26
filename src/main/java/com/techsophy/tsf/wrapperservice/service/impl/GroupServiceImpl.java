package com.techsophy.tsf.wrapperservice.service.impl;

import com.fasterxml.jackson.core.JsonProcessingException;
import com.fasterxml.jackson.databind.ObjectMapper;
import com.techsophy.tsf.wrapperservice.config.CamundaModifiedPathURL;
import com.techsophy.tsf.wrapperservice.config.GlobalMessageSource;
import com.techsophy.tsf.wrapperservice.dto.*;
import com.techsophy.tsf.wrapperservice.exception.InvalidInputException;
import com.techsophy.tsf.wrapperservice.service.GroupService;
import com.techsophy.tsf.wrapperservice.utils.TokenUtils;
import com.techsophy.tsf.wrapperservice.utils.WebClientWrapper;
import lombok.RequiredArgsConstructor;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.stereotype.Service;

import java.util.List;

import static com.techsophy.tsf.wrapperservice.constants.ApplicationConstants.CAMUNDA_SERVLET_CONTEXT_PATH_VARIABLE;
import static com.techsophy.tsf.wrapperservice.constants.ApplicationConstants.GATEWAY_URI_VARIABLE;
import static com.techsophy.tsf.wrapperservice.constants.ApplicationEndpointConstants.GET;
import static com.techsophy.tsf.wrapperservice.constants.CamundaApiConstants.*;
import static com.techsophy.tsf.wrapperservice.constants.ErrorConstants.*;

@Service
@RequiredArgsConstructor(onConstructor_ = {@Autowired})
public class GroupServiceImpl implements GroupService {

    private final WebClientWrapper webClientWrapper;
    private final TokenUtils tokenUtils;
    private final ObjectMapper objectMapper;
    private final GlobalMessageSource globalMessageSource;
    private final CamundaModifiedPathURL camundaModifiedPathURL;

    @Value(CAMUNDA_SERVLET_CONTEXT_PATH_VARIABLE)
    private String camundaServletContextPath;

    @Value(GATEWAY_URI_VARIABLE)
    private String gatewayURI;
    @Override
    public TaskCountDTO groupCount(GroupTaskCountReqDTO groupTaskCountReqDTO) throws JsonProcessingException {

        String url = camundaModifiedPathURL.getCamundaPathUri(GET_GROUP_TASK_COUNT);
        var webClient =webClientWrapper.createWebClient(tokenUtils.getTokenFromContext());
        String response = webClientWrapper.webclientRequest(webClient,url,POST, groupTaskCountReqDTO);
        if(response.isBlank()) {
            throw new InvalidInputException(UNABLE_TO_RETRIEVE_COUNT,globalMessageSource.get(UNABLE_TO_RETRIEVE_COUNT));
        }
        return this.objectMapper.readValue(response, TaskCountDTO.class);
    }

    @Override
    public List<GroupTaskDTO> groupTask(GroupTaskCountReqDTO groupTaskDTO, Integer firstResult, Integer maxResults) throws JsonProcessingException {

        String url = camundaModifiedPathURL.getCamundaPathUri(GET_GROUP_TASK+FIRST_RESULT_GROUP_TASK+firstResult+MAX_RESULTS_GROUP_TASK+maxResults);
        var webClient =webClientWrapper.createWebClient(tokenUtils.getTokenFromContext());
        String response=webClientWrapper.webclientRequest(webClient,url,POST,groupTaskDTO);
        if(response.isBlank())
        {
            throw new InvalidInputException(UNABLE_TO_FETCH_GROUP_TASKS,globalMessageSource.get(UNABLE_TO_FETCH_GROUP_TASKS));
        }

        return this.objectMapper.readValue(response,List.class);

    }

    @Override
    public List<GroupTaskHistoryDTO> groupTaskHistory(String caseInstanceId) throws JsonProcessingException {
        String url = camundaModifiedPathURL.getCamundaPathUri(GROUP_TASK_CASE_INSTANCE+CASE_INSTANCE_ID+caseInstanceId);
        var webClient=webClientWrapper.createWebClient(tokenUtils.getTokenFromContext());
        String response=webClientWrapper.webclientRequest(webClient,url,GET,caseInstanceId);
        if(response.isBlank())
        {
            throw new InvalidInputException(UNABLE_TO_FETCH_CASE_INSTANCE_BY_ID,globalMessageSource.get(UNABLE_TO_FETCH_CASE_INSTANCE_BY_ID));
        }

        return this.objectMapper.readValue(response,List.class);
    }

    @Override
    public GroupCaseInstanceDTO groupCaseInstance(String id) throws JsonProcessingException {
        String url = camundaModifiedPathURL.getCamundaPathUri(GROUP_CASE_INSTANCE+id);
        var webClient=webClientWrapper.createWebClient(tokenUtils.getTokenFromContext());
        String response=webClientWrapper.webclientRequest(webClient,url,GET,id);
        if(response.isBlank())
        {
            throw new InvalidInputException(UNABLE_TO_FETCH_CASE_INSTANCE_BY_ID,globalMessageSource.get(UNABLE_TO_FETCH_CASE_INSTANCE_BY_ID));
        }

        return this.objectMapper.readValue(response, GroupCaseInstanceDTO.class);
    }

    @Override
    public GroupFormDTo groupFormVariables(String id) throws JsonProcessingException {
        String url = camundaModifiedPathURL.getCamundaPathUri(GROUP_FORM_VARIABLE+id+FORM_VARIABLE);
        var webClient=webClientWrapper.createWebClient(tokenUtils.getTokenFromContext());
        String response=webClientWrapper.webclientRequest(webClient,url,GET,id);
        if(response.isBlank())
        {
            throw new InvalidInputException(UNABLE_TO_FETCH_CASE_INSTANCE_BY_ID,globalMessageSource.get(UNABLE_TO_FETCH_CASE_INSTANCE_BY_ID));
        }

        return this.objectMapper.readValue(response, GroupFormDTo.class);
    }

    @Override
    public AllTaskCaseInstanceDTO groupVariables(String id) throws JsonProcessingException {
        String url = camundaModifiedPathURL.getCamundaPathUri(GROUP_CASE_INSTANCE+id+VARIABLES);
        var webClient=webClientWrapper.createWebClient(tokenUtils.getTokenFromContext());
        String response=webClientWrapper.webclientRequest(webClient,url,GET,id);
        if(response.isBlank())
        {
            throw new InvalidInputException(UNABLE_TO_FETCH_CASE_INSTANCE_BY_ID,globalMessageSource.get(UNABLE_TO_FETCH_CASE_INSTANCE_BY_ID));
        }

        return this.objectMapper.readValue(response, AllTaskCaseInstanceDTO.class);
    }

    @Override
    public GroupTaskCaseDefinition groupCaseDefinition(String id) throws JsonProcessingException {
        String url = camundaModifiedPathURL.getCamundaPathUri(GROUP_CASE_DEFINITION+id);
        var webClient=webClientWrapper.createWebClient(tokenUtils.getTokenFromContext());
        String response=webClientWrapper.webclientRequest(webClient,url,GET,id);
        if(response.isBlank())
        {
            throw new InvalidInputException(UNABLE_TO_FETCH_CASE_INSTANCE_BY_ID,globalMessageSource.get(UNABLE_TO_FETCH_CASE_INSTANCE_BY_ID));
        }

        return this.objectMapper.readValue(response, GroupTaskCaseDefinition.class);
    }
}
