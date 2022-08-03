package com.techsophy.tsf.wrapperservice.dto;

import lombok.Value;
import lombok.With;
import java.util.Map;

/**
 * process instance response DTO
 */
@Value
@With
public class ProcessInstanceResponseDTO
{
    String processInstanceId;
    String businessKey;
    Map<String, Object> variables;
    Boolean alreadyExists;
}