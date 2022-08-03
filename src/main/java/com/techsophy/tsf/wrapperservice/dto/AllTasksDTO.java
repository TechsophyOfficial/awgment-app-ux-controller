package com.techsophy.tsf.wrapperservice.dto;

import lombok.Data;

@Data
public class AllTasksDTO {
    private String id;
    private String name;
    private String assignee;
    private String created;
    private String due;
    private String followUp;
    private String delegationState;
    private String description;
    private String executionId;
    private String owner;
    private String parentTaskId;
    private String priority;
    private String processDefinitionId;
    private String processInstanceId;
    private String taskDefinitionKey;
    private String caseExecutionId;
    private String caseInstanceId;
    private String caseDefinitionId;
    private String suspended;
    private String formKey;
    private String tenantId;
}
