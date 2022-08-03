package com.techsophy.tsf.wrapperservice.dto;

import lombok.Value;
import java.util.Map;

/**
 * Generice DTO
 */
@Value
public class GenericDTO
{
    String businessKey;
    String taskId;
    Map<String,Object> variables;
}
