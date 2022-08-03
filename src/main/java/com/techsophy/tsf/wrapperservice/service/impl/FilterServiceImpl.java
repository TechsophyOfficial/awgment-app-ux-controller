package com.techsophy.tsf.wrapperservice.service.impl;


import com.fasterxml.jackson.core.JsonProcessingException;
import com.fasterxml.jackson.databind.ObjectMapper;
import com.techsophy.tsf.wrapperservice.config.GlobalMessageSource;
import com.techsophy.tsf.wrapperservice.constants.MessageConstants;
import com.techsophy.tsf.wrapperservice.dto.*;
import com.techsophy.tsf.wrapperservice.service.FilterService;
import com.techsophy.tsf.wrapperservice.utils.TokenUtils;
import com.techsophy.tsf.wrapperservice.utils.WebClientWrapper;
import lombok.AllArgsConstructor;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.cloud.context.config.annotation.RefreshScope;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.stereotype.Service;
import org.springframework.web.reactive.function.client.WebClient;

import java.math.BigInteger;
import java.util.List;
import java.util.Map;
import java.util.stream.Collectors;

import static com.techsophy.tsf.wrapperservice.constants.ApplicationConstants.CAMUNDA_SERVLET_CONTEXT_PATH_VARIABLE;
import static com.techsophy.tsf.wrapperservice.constants.ApplicationConstants.GATEWAY_URI_VARIABLE;
import static com.techsophy.tsf.wrapperservice.constants.ApplicationEndpointConstants.FILTER;
import static com.techsophy.tsf.wrapperservice.constants.ApplicationEndpointConstants.FORM_VARIABLES;
import static com.techsophy.tsf.wrapperservice.constants.CamundaApiConstants.*;
import static com.techsophy.tsf.wrapperservice.constants.MessageConstants.*;

@RefreshScope
@Service
@AllArgsConstructor(onConstructor_ = {@Autowired})
public class FilterServiceImpl implements FilterService
{
    TokenUtils tokenUtils;
    WebClientWrapper webClientWrapper;
    TokenUtils utils;
    ObjectMapper objectMapper;
    GlobalMessageSource globalMessageSource;
    @Value(CAMUNDA_SERVLET_CONTEXT_PATH_VARIABLE)
    private String camundaServletContextPath;

    @Value(GATEWAY_URI_VARIABLE)
    private String gatewayURI;

    @Override
    public FilterResponseDTO createFilter(FilterDTO createFilterDTO) throws JsonProcessingException
    {
        String url = gatewayURI + camundaServletContextPath +GET_CREATE_FILTER;
        WebClient webClient=webClientWrapper.createWebClient(tokenUtils.getTokenFromContext());
        String response = webClientWrapper.webclientRequest(webClient,url,POST,createFilterDTO);
        return  this.objectMapper.readValue(response, FilterResponseDTO.class);
    }

    @Override
    public List<Map<String, Object>> executeFilter(String id, ExecuteFilterDTO executeFilterDTO, String emptyString, String firstResult, String maxResult, boolean involvedUser) throws JsonProcessingException {
        String requestURL=" ";
        WebClient webClient=webClientWrapper.createWebClient(tokenUtils.getTokenFromContext());
        String loggedInUserDetails =tokenUtils.getLoggedInUserId();

        if((firstResult!=null) && (maxResult!=null) && involvedUser==false )
        {
            requestURL = gatewayURI + camundaServletContextPath+GET_EXECUTE_FILTER_LIST + URL_SEPERATOR + id + URL_SEPERATOR + LIST + "?"+emptyString+"&"+ FIRST_RESULT + "=" + firstResult + "&" + MAX_RESULTS + "=" + maxResult;

        }
        else
        {
            requestURL = gatewayURI + camundaServletContextPath+GET_EXECUTE_FILTER_LIST + URL_SEPERATOR + id + URL_SEPERATOR + LIST;

        }
        String response = webClientWrapper.webclientRequest(webClient,requestURL,POST,executeFilterDTO);
        List<Map<String,Object>>  filterLIst = this.objectMapper.readValue(response,List.class);


        if(involvedUser)
        {

            return filterLIst.stream().filter(fi-> fi.get("assignee")==null || !(fi.get("assignee").equals("bhavya"))).collect(Collectors.toList());
        }
        return filterLIst;
    }
    @Override
    public FilterResponseDTO getFilterById(String id) throws JsonProcessingException
    {
        String url = gatewayURI + camundaServletContextPath +ENGINE_REST+FILTER+URL_SEPERATOR+id;
        WebClient webClient=webClientWrapper.createWebClient(tokenUtils.getTokenFromContext());
        String response = webClientWrapper.webclientRequest(webClient,url,GET,null);
        return  this.objectMapper.readValue(response, FilterResponseDTO.class);
    }

    @Override
    public FilterCountResponseDTO filterCount(String id,FilterCountDTO filterCountDTO) throws JsonProcessingException {
        String url = gatewayURI + camundaServletContextPath +ENGINE_REST+FILTER+URL_SEPERATOR+id+MessageConstants.COUNT;
        WebClient webClient=webClientWrapper.createWebClient(tokenUtils.getTokenFromContext());
        String response = webClientWrapper.webclientRequest(webClient,url,POST,filterCountDTO);
        return  this.objectMapper.readValue(response, FilterCountResponseDTO.class);
    }

    @Override
    public Map<String, Object> getFormVariables(String id) throws JsonProcessingException
    {
        String url = gatewayURI + camundaServletContextPath +ENGINE_REST+TASK+URL_SEPERATOR+id+FORM_VARIABLES;
        WebClient webClient=webClientWrapper.createWebClient(tokenUtils.getTokenFromContext());
        String response = webClientWrapper.webclientRequest(webClient,url,GET,null);
        return  this.objectMapper.readValue(response, Map.class);
    }

}
