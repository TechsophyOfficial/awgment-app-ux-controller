package com.techsophy.tsf.wrapperservice.dto;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@AllArgsConstructor
@NoArgsConstructor
public class TaskDto {
    private String id;
    private String name;
    private String description;
    private String assignee;
    private String owner;
    private String delegationState;
    private String due;
    private String followUp;
    private long priority;
    private String parentTaskId;
    private String caseInstanceId;
    private String tenantId;
}
