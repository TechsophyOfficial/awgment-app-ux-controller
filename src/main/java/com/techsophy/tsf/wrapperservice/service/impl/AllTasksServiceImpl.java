package com.techsophy.tsf.wrapperservice.service.impl;

import com.fasterxml.jackson.core.JsonProcessingException;
import com.fasterxml.jackson.databind.ObjectMapper;
import com.techsophy.tsf.wrapperservice.config.TenantWorkflowResolver;
import com.techsophy.tsf.wrapperservice.config.GlobalMessageSource;
import com.techsophy.tsf.wrapperservice.dto.*;
import com.techsophy.tsf.wrapperservice.exception.InvalidInputException;
import com.techsophy.tsf.wrapperservice.service.AllTasksService;
import com.techsophy.tsf.wrapperservice.utils.TokenUtils;
import com.techsophy.tsf.wrapperservice.utils.WebClientWrapper;
import lombok.RequiredArgsConstructor;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.stereotype.Service;
import java.util.List;
import static com.techsophy.tsf.wrapperservice.constants.ApplicationConstants.CAMUNDA_SERVLET_CONTEXT_PATH_VARIABLE;
import static com.techsophy.tsf.wrapperservice.constants.ApplicationConstants.GATEWAY_URI_VARIABLE;
import static com.techsophy.tsf.wrapperservice.constants.CamundaApiConstants.*;
import static com.techsophy.tsf.wrapperservice.constants.ErrorConstants.*;
import static com.techsophy.tsf.wrapperservice.constants.MessageConstants.GET;

@Service
@RequiredArgsConstructor(onConstructor_ ={@Autowired} )
public class AllTasksServiceImpl implements AllTasksService {
    private final WebClientWrapper webClientWrapper;
    private final TokenUtils tokenUtils;
    private final ObjectMapper objectMapper;
    private final GlobalMessageSource globalMessageSource;
    private final TenantWorkflowResolver tenantWorkflowResolver;

    @Value(CAMUNDA_SERVLET_CONTEXT_PATH_VARIABLE)
    private String camundaServletContextPath;

    @Value(GATEWAY_URI_VARIABLE)
    private String gatewayURI;
    @Override
    public TaskCountDTO allTasksCount(AllTasksCountDTO allTasksCountDTO) throws JsonProcessingException {
        String url = tenantWorkflowResolver.getCamundaPathUri(GET_TASK_COUNT);
        var webClient=webClientWrapper.createWebClient(tokenUtils.getTokenFromContext());
        String response=webClientWrapper.webclientRequest(webClient,url,POST, allTasksCountDTO);
        if(response.isBlank())
            throw new InvalidInputException(UNABLE_TO_RETRIEVE_COUNT,globalMessageSource.get(UNABLE_TO_RETRIEVE_COUNT));

        return this.objectMapper.readValue(response,TaskCountDTO.class);
    }

    @Override
    public List<AllTasksDTO> allTasks(AllTasksCountDTO allTasksCountDTO, Integer firstResult, Integer maxResults) throws JsonProcessingException {
        String url = tenantWorkflowResolver.getCamundaPathUri(GET_GROUP_TASK+FIRSTRESULT+firstResult+MAXRESULT+maxResults);
        var webClient =webClientWrapper.createWebClient(tokenUtils.getTokenFromContext());
        String response=webClientWrapper.webclientRequest(webClient,url,POST,allTasksCountDTO);
        if(response.isBlank())
        {
            throw new InvalidInputException(UNABLE_TO_FETCH_ALL_TASKS,globalMessageSource.get(UNABLE_TO_FETCH_GROUP_TASKS));
        }

        return this.objectMapper.readValue(response,List.class);
    }

    @Override
    public AllTaskCaseInstanceDTO allTaskCaseInstance(String id) throws JsonProcessingException {
        String url = tenantWorkflowResolver.getCamundaPathUri(GET_GROUP_TASK_CASEINSTANCE+id+VARIABLES);
        var webClient =webClientWrapper.createWebClient(tokenUtils.getTokenFromContext());
        String response=webClientWrapper.webclientRequest(webClient,url,GET,id);
        if(response.isBlank())
        {
            throw new InvalidInputException(UNABLE_TO_FETCH_ALL_TASKS,globalMessageSource.get(UNABLE_TO_FETCH_GROUP_TASKS));
        }

        return this.objectMapper.readValue(response,AllTaskCaseInstanceDTO.class);
    }

    @Override
    public AllTaskFormsDTO allTaskVariables(String id) throws JsonProcessingException {
        String url = tenantWorkflowResolver.getCamundaPathUri(ALLTASK_CASE_INSTANCE+id+VARIABLES);
        var webClient =webClientWrapper.createWebClient(tokenUtils.getTokenFromContext());
        String response=webClientWrapper.webclientRequest(webClient,url,GET,id);
            if(response.isBlank())
        {
            throw new InvalidInputException(UNABLE_TO_FETCH_ALL_TASKS,globalMessageSource.get(UNABLE_TO_FETCH_GROUP_TASKS));
        }

        return this.objectMapper.readValue(response,AllTaskFormsDTO.class);
    }

    @Override
    public AllTaskFormVariablesDTO allTaskFormVariables(String id) throws JsonProcessingException {
        String url = tenantWorkflowResolver.getCamundaPathUri(ALLTASK_FORM_VARIABLES+id+FORM_VARIABLE);
        var webClient =webClientWrapper.createWebClient(tokenUtils.getTokenFromContext());
        String response=webClientWrapper.webclientRequest(webClient,url,GET,id);
        if(response.isBlank())
        {
            throw new InvalidInputException(UNABLE_TO_FETCH_ALL_TASKS,globalMessageSource.get(UNABLE_TO_FETCH_GROUP_TASKS));
        }

        return this.objectMapper.readValue(response,AllTaskFormVariablesDTO.class);
    }

    @Override
    public List<CaseActivityInstanceDTO> allTaskCaseActivityInstance(String caseInstanceId) throws JsonProcessingException {
        String url = tenantWorkflowResolver.getCamundaPathUri(ALLTASK_CASE_ACTIVITY_INSTANCE);
        var webClient =webClientWrapper.createWebClient(tokenUtils.getTokenFromContext());
        String response=webClientWrapper.webclientRequest(webClient,url+ALLTASKCASEINSTANCEID+caseInstanceId,GET,caseInstanceId);
        if(response.isBlank())
        {
            throw new InvalidInputException(UNABLE_TO_FETCH_ALL_TASKS,globalMessageSource.get(UNABLE_TO_FETCH_GROUP_TASKS));
        }

        return this.objectMapper.readValue(response,List.class);
    }
}
