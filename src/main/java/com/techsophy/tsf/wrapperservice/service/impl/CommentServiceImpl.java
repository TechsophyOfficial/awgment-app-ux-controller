package com.techsophy.tsf.wrapperservice.service.impl;

import com.fasterxml.jackson.core.type.TypeReference;
import com.fasterxml.jackson.databind.ObjectMapper;
import com.techsophy.tsf.wrapperservice.constants.ApplicationConstants;
import com.techsophy.tsf.wrapperservice.constants.CamundaApiConstants;
import com.techsophy.tsf.wrapperservice.dto.ApiResponse;
import com.techsophy.tsf.wrapperservice.dto.CommentDTO;
import com.techsophy.tsf.wrapperservice.dto.TaskQueryDTO;
import com.techsophy.tsf.wrapperservice.model.TaskModel;
import com.techsophy.tsf.wrapperservice.model.TaskQuery;
import com.techsophy.tsf.wrapperservice.service.CommentService;
import com.techsophy.tsf.wrapperservice.service.ProcessService;
import com.techsophy.tsf.wrapperservice.utils.CommonUtils;
import lombok.RequiredArgsConstructor;
import lombok.SneakyThrows;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.cloud.context.config.annotation.RefreshScope;
import org.springframework.http.*;
import org.springframework.stereotype.Service;
import org.springframework.web.client.RestTemplate;
import org.springframework.web.util.UriComponentsBuilder;
import java.util.ArrayList;
import java.util.List;
import java.util.Map;
import static com.techsophy.tsf.wrapperservice.config.TokenConfig.getBearerToken;
import static com.techsophy.tsf.wrapperservice.constants.ApplicationConstants.*;
import static com.techsophy.tsf.wrapperservice.constants.MessageConstants.FAILED_WHILE_FETCHING_TASK;
import static com.techsophy.tsf.wrapperservice.constants.MessageConstants.MISSING_MANDATORY_PARAMS;

/**
 * Comment service
 */
@RefreshScope
@Service
@RequiredArgsConstructor
public class CommentServiceImpl implements CommentService
{
    @Value(ApplicationConstants.CAMUNDA_SERVLET_CONTEXT_PATH_VARIABLE)
    private String camundaServletContextPath;

    @Value(ApplicationConstants.GATEWAY_URI_VARIABLE)
    private String gatewayURI;

    private final RestTemplate restTemplate;
    private final ObjectMapper objectMapper;
    private final ProcessService processService;

    /**
     * create comment
     * @param comment
     * @return Map
     */
    @Override
    @SneakyThrows
    public Map<String, Object> createComment(CommentDTO comment)
    {
        boolean isTaskIdAndProcessInstanceIdProvided = CommonUtils.isValidString(comment.getTaskId()) && CommonUtils.isValidString(comment.getProcessInstanceId());
        boolean isBusinessKeyProvided = CommonUtils.isValidString(comment.getBusinessKey());
        if(!isTaskIdAndProcessInstanceIdProvided && !isBusinessKeyProvided)
        {
            throw new IllegalArgumentException(MISSING_MANDATORY_PARAMS);
        }
        if(!(isTaskIdAndProcessInstanceIdProvided || !isBusinessKeyProvided))
        {
            TaskModel task = this.getTask(comment.getBusinessKey());
            comment.setTaskId(task.getId());
            comment.setProcessInstanceId(task.getProcessInstanceId());
        }
        String url = gatewayURI + camundaServletContextPath + CamundaApiConstants.CREATE_COMMENT;
        UriComponentsBuilder builder = UriComponentsBuilder.fromHttpUrl(url);
        HttpHeaders httpHeaders = new HttpHeaders();
        httpHeaders.add(HttpHeaders.AUTHORIZATION, getBearerToken());
        httpHeaders.setContentType(MediaType.APPLICATION_JSON);
        HttpEntity<?> httpEntity = new HttpEntity<>(this.objectMapper.writeValueAsString(comment),httpHeaders);
        ResponseEntity<Object> response = restTemplate.exchange(builder.toUriString(), HttpMethod.POST, httpEntity, Object.class);
        if(response.getStatusCode().is2xxSuccessful())
        {
            var apiResponse = this.objectMapper.convertValue(response.getBody(), ApiResponse.class);
            return this.objectMapper.convertValue(apiResponse.getData(), Map.class);
        }
        return this.objectMapper.convertValue(response, Map.class);
    }
    /**
     * get commnets by process instance id & case instance id & business key
     * @param processInstanceId
     * @param caseInstanceId
     * @param businessKey
     * @return Object
     */
    @Override
    public Object getComments(String processInstanceId,String caseInstanceId, String businessKey)
    {
       if(!CommonUtils.isValidString(businessKey) && !CommonUtils.isValidString(processInstanceId) && !CommonUtils.isValidString(caseInstanceId))
        {
            throw new IllegalArgumentException(MISSING_MANDATORY_PARAMS);
        }
        String url = gatewayURI + camundaServletContextPath + CamundaApiConstants.GET_COMMENT;
        UriComponentsBuilder builder = UriComponentsBuilder.fromHttpUrl(url)
                .queryParam(PROCESSINSTANCEID, processInstanceId)
                .queryParam(CASEINSTANCEID,caseInstanceId)
                .queryParam(BUSINESSKEY,businessKey);
        HttpHeaders httpHeaders = new HttpHeaders();
        httpHeaders.add(HttpHeaders.AUTHORIZATION, getBearerToken());
        httpHeaders.setContentType(MediaType.APPLICATION_JSON);
        HttpEntity<?> httpEntity = new HttpEntity<>(httpHeaders);
        ResponseEntity<Object> response = restTemplate.exchange(builder.toUriString(), HttpMethod.GET, httpEntity, Object.class);
        if(response.getStatusCode().is2xxSuccessful())
        {
            var apiResponse = this.objectMapper.convertValue(response.getBody(), ApiResponse.class);
            return this.objectMapper.convertValue(apiResponse.getData(), new TypeReference<>()
            {
            });
        }
        return this.objectMapper.convertValue(response, Map.class);
    }

    /**
     * get tasks based on business key
     * @param businessKey
     * @return List
     */
    private TaskModel getTask(String businessKey)
    {
        TaskQueryDTO taskQuery = new TaskQueryDTO();
        List<Object> list=new ArrayList<>();
        TaskQuery obj=new TaskQuery();

        obj.setProcessInstanceBusinessKey(businessKey);
        obj.setCaseInstanceBusinessKey(businessKey);
        list.add(obj);
        taskQuery.setOrQueries(list);
        Object taskResponse = processService.getTasksByQuery(taskQuery, 0, 1).getRows();
        List<TaskModel> tasks =  this.objectMapper.convertValue(taskResponse, new TypeReference<>()
        {
        });
        if(!tasks.isEmpty())
        {
            return tasks.get(0);
        }
        else
        {
            throw new IllegalArgumentException(FAILED_WHILE_FETCHING_TASK);
        }
    }
}
