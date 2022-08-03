package com.techsophy.tsf.wrapperservice.model;


import com.fasterxml.jackson.annotation.JsonInclude;
import lombok.Data;

/**
 * Task Query
 */
@Data
@JsonInclude(JsonInclude.Include.NON_NULL)
public class TaskQuery
{
    private String  processInstanceBusinessKey;
    private String caseInstanceBusinessKey;
    private String processInstanceId;
    private String caseInstanceId;
}
