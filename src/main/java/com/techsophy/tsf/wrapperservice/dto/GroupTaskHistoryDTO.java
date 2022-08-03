package com.techsophy.tsf.wrapperservice.dto;

import java.sql.Time;
import java.util.Date;

public class GroupTaskHistoryDTO {
    private String id;
    private String parentCaseActivityInstanceId;
    private String caseActivityId;
    private String caseActivityName;
    private String caseActivityType;
    private String caseDefinitionId;
    private String caseInstanceId;
    private String caseExecutionId;
    private String taskId;
    private String calledProcessInstanceId;
    private String calledCaseInstanceId;
    private String tenantId;
    private Date createTime;
    private Date endTime;
    private Time durationInMillis;
    private Boolean required;
    private Boolean available;
    private Boolean enabled;
    private Boolean disabled;
    private Boolean active;
    private Boolean completed;
    private Boolean terminated;
}
