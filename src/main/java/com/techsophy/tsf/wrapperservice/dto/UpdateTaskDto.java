package com.techsophy.tsf.wrapperservice.dto;


import com.fasterxml.jackson.annotation.JsonIgnoreProperties;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@AllArgsConstructor
@NoArgsConstructor
@JsonIgnoreProperties(ignoreUnknown = true)
public class UpdateTaskDto {

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
