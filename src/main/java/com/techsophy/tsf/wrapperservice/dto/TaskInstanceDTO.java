package com.techsophy.tsf.wrapperservice.dto;

import lombok.Value;

import java.util.Date;
import java.util.List;

/**
 * Task instance DTO
 */
@Value
public class TaskInstanceDTO
{
    String id;
    String name;
    String assignee;
    Date created;
    Date due;
    Date followUp;
    String delegationState;
    String description;
    String executionId;
    String owner;
    String parentTaskId;
    int priority;
    String processDefinitionId;
    String processInstanceId;
    String taskDefinitionKey;
    String caseExecutionId;
    String caseInstanceId;
    String caseDefinitionId;
    boolean suspended;
    String formKey;
    String tenantId;
    String businessKey;
    String componentId;
    String componentType;
    String checklistInstanceId;
    String question;
    List<ActionsDTO> actions;
}