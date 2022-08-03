package com.techsophy.tsf.wrapperservice.dto;


import lombok.Data;

@Data
public class TaskHistoryDto {
    private String id;
    private String userId;
    private String timestamp;
    private String operationId;
    private String operationType;
    private String entityType;
    private String category;
    private String annotation;
    private String property;
    private String orgValue;
    private String newValue;
    private String deploymentId;
    private String processDefinitionId;
    private String processDefinitionKey;
    private String processInstanceId;
    private String executionId;
    private String caseDefinitionId;
    private String caseInstanceId;
    private String caseExecutionId;
    private String taskId;
    private String externalTaskId;
    private String batchId;
    private String jobId;
    private String jobDefinitionId;
    private String removalTime;
    private String rootProcessInstanceId;

}

