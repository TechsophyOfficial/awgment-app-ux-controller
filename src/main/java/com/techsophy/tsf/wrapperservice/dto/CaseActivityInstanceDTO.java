package com.techsophy.tsf.wrapperservice.dto;

import lombok.Data;

@Data
public class CaseActivityInstanceDTO {
    String id;
    String parentCaseActivityInstanceId;
    String caseActivityId;
    String caseActivityName;
    String caseActivityType;
    String caseDefinitionId;
    String caseInstanceId;
    String caseExecutionId;
    String taskId;
    String calledProcessInstanceId;
    String calledCaseInstanceId;
    String tenantId;
    String createTime;
    String endTime;
    String durationInMillis;
    String required;
    String available;
    String enabled;
    String disabled;
    String active;
    String completed;
    String terminated;

}

