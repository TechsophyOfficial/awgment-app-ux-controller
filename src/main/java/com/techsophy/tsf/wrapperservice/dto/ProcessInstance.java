package com.techsophy.tsf.wrapperservice.dto;

import lombok.Value;
import lombok.With;
import java.util.Map;

/**
 * process instance
 */
@Value
@With

public class ProcessInstance
{
    String processDefinitionKey;
    String businessKey;
    Map<String, Object> variables;
}
