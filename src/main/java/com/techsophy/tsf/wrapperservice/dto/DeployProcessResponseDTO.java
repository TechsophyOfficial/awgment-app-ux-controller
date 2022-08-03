package com.techsophy.tsf.wrapperservice.dto;

import lombok.*;

import java.sql.Time;
import java.util.List;

@With
@Value
public class DeployProcessResponseDTO
{
    Object deployedCaseDefinitions;
    Object deployedDecisionDefinitions;
    Object deployedDecisionRequirementsDefinitions;
    Object deployedProcessDefinitions;
    Time deploymentTime;
    String id;
    List<Object> links;
    String name;
    String source;
    String tenantId;
}
