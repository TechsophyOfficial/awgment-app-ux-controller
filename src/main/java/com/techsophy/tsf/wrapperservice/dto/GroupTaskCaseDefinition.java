package com.techsophy.tsf.wrapperservice.dto;

import lombok.Data;

@Data
public class GroupTaskCaseDefinition {
    String id;
    String key;
    String category;
    String name;
    String version;
    String resource;
    String deploymentId;
    String tenantId;
    String historyTimeToLive;
}
