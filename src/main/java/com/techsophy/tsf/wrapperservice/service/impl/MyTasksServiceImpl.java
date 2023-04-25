package com.techsophy.tsf.wrapperservice.service.impl;

import com.fasterxml.jackson.core.JsonProcessingException;
import com.fasterxml.jackson.databind.ObjectMapper;
import com.techsophy.tsf.wrapperservice.config.UxControllerCamundaServletContextPath;
import com.techsophy.tsf.wrapperservice.config.GlobalMessageSource;
import com.techsophy.tsf.wrapperservice.dto.MyTasksDTO;
import com.techsophy.tsf.wrapperservice.service.MyTasksService;
import com.techsophy.tsf.wrapperservice.utils.TokenUtils;
import com.techsophy.tsf.wrapperservice.utils.WebClientWrapper;
import lombok.AllArgsConstructor;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.cloud.context.config.annotation.RefreshScope;
import org.springframework.stereotype.Service;
import org.springframework.web.reactive.function.client.WebClient;

import java.util.List;
import java.util.Map;

import static com.techsophy.tsf.wrapperservice.constants.ApplicationConstants.CAMUNDA_SERVLET_CONTEXT_PATH_VARIABLE;
import static com.techsophy.tsf.wrapperservice.constants.ApplicationConstants.GATEWAY_URI_VARIABLE;
import static com.techsophy.tsf.wrapperservice.constants.CamundaApiConstants.*;
import static com.techsophy.tsf.wrapperservice.constants.MessageConstants.*;


@RefreshScope
@Service
@AllArgsConstructor(onConstructor_ = {@Autowired})
public class MyTasksServiceImpl implements MyTasksService
{
    WebClientWrapper webClientWrapper;
    TokenUtils tokenUtils;
    ObjectMapper objectMapper;
    GlobalMessageSource globalMessageSource;
    private final UxControllerCamundaServletContextPath uxControllerCamundaServletContextPath;
    @Value(CAMUNDA_SERVLET_CONTEXT_PATH_VARIABLE)
    private String camundaServletContextPath;

    @Value(GATEWAY_URI_VARIABLE)
    private String gatewayURI;

    @Override
    public String myTasksCount(MyTasksDTO myTasksDTO)
    {
        String url = gatewayURI + uxControllerCamundaServletContextPath.getCamundaPathUri() + ENGINE_REST+GET_MY_TASK_COUNT;
        WebClient webClient=webClientWrapper.createWebClient(tokenUtils.getTokenFromContext());
        String response = webClientWrapper.webclientRequest(webClient,url,POST, myTasksDTO);
        return objectMapper.convertValue(response,String.class);
    }

    @Override
    public List<Map<String, Object>> getAllTasks(MyTasksDTO myTasksDTO, String firstResult, String maxResult) throws JsonProcessingException
    {
        WebClient webClient=webClientWrapper.createWebClient(tokenUtils.getTokenFromContext());
        String requestURL = gatewayURI + uxControllerCamundaServletContextPath.getCamundaPathUri()+ENGINE_REST+TASK+ "?"+ FIRST_RESULT + "=" + firstResult + "&" + MAX_RESULTS + "=" + maxResult;
        String response = webClientWrapper.webclientRequest(webClient,requestURL,POST,myTasksDTO);
        return  this.objectMapper.readValue(response,List.class);
    }

    @Override
    public List<Map<String, Object>> getMyTasksHistory(String caseInstanceId) throws JsonProcessingException
    {
        WebClient webClient=webClientWrapper.createWebClient(tokenUtils.getTokenFromContext());
        String requestURL = gatewayURI + uxControllerCamundaServletContextPath.getCamundaPathUri()+ENGINE_REST+HISTORY+CASE_ACTIVITY_INSTANCE+CASE_INSTANCE_ID+caseInstanceId;
        String response = webClientWrapper.webclientRequest(webClient,requestURL,GET,null);
        return  this.objectMapper.readValue(response,List.class);
    }

    @Override
    public Map<String, Object> getMyTasksById(String id) throws JsonProcessingException {
        WebClient webClient=webClientWrapper.createWebClient(tokenUtils.getTokenFromContext());
        String requestURL = gatewayURI + uxControllerCamundaServletContextPath.getCamundaPathUri()+ENGINE_REST+URL_SEPERATOR+CASE_INSTANCE+URL_SEPERATOR+id;
        String response = webClientWrapper.webclientRequest(webClient,requestURL,GET,null);
        return  this.objectMapper.readValue(response,Map.class);

    }
}
