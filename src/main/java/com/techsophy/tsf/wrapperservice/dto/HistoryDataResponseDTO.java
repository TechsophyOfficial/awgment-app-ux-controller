package com.techsophy.tsf.wrapperservice.dto;

import lombok.Data;
import lombok.Value;
import lombok.With;

import java.time.Instant;
import java.util.List;
import java.util.Map;

@Data
public class HistoryDataResponseDTO {
    String  id;
    String  processDefinitionKey;
    String  processDefinitionId ;
    String  processInstanceId;
    String  executionId;
    String  caseDefinitionKey ;
    String  caseDefinitionId;
    String  caseInstanceId;
    String  caseExecutionId;
    String  activityInstanceId;
    String  name;
    String  description;
    String  deleteReason;
    String  owner;
    String  assignee;
    Instant startTime;
    Instant endTime;
    Integer duration;
    String  taskDefinitionKey;
    Integer priority;
    String  due ;
    String  parentTaskId;
    String  followUp;
    String  tenantId;
    String  removalTime;
    String  rootProcessInstanceId;
}
